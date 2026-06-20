mod config;
mod metrics;
mod random;
mod summary;
mod taskgen_process;

use config::{parse_config, resolve_seed};
use health_monitor::HealthMonitor;
use metrics::update_atomic_max;
use summary::{print_summary, SummaryData};
use std::collections::HashSet;
use std::env;
use std::sync::atomic::{AtomicBool, AtomicU64, Ordering};
use std::sync::Arc;
use std::thread;
use std::time::{Duration, Instant};
use task_queue::TaskQueue;
use zone_control::ZoneManager;


fn main() {
    if env::args().any(|arg| arg == "--taskgen-child") { 
        // Task generator child process mode: generate tasks and write to stdout as JSON lines.
        taskgen::run_taskgen_child();
        return;
    }

    // 1) Parse CLI and initialize shared coordination services.
    let cfg = parse_config();
    let simulation_start = Instant::now();
    let task_queue = Arc::new(TaskQueue::new_with_lookahead(cfg.lookahead));
    let zone_manager = Arc::new(ZoneManager::new());
    let health_monitor = Arc::new(HealthMonitor::new(
        Duration::from_millis(cfg.timeout_ms),
        Duration::from_millis(300),
    ));
    health_monitor.start_checker_thread();

    let running = Arc::new(AtomicBool::new(true));
    let initial_seed = resolve_seed(cfg.seed_mode);
    let generated_tasks = Arc::new(AtomicU64::new(0));
    let completed_tasks = Arc::new(AtomicU64::new(0));
    let completed_emergency_tasks = Arc::new(AtomicU64::new(0));
    let workload_fingerprint = Arc::new(AtomicU64::new(0xCBF29CE484222325));
    let peak_queue_length = Arc::new(AtomicU64::new(0));
    let zone_contention_count = Arc::new(AtomicU64::new(0));
    let scheduler_deferral_count = Arc::new(AtomicU64::new(0));
    let zone_lock_deferral_count = Arc::new(AtomicU64::new(0));
    let zone_acquire_success_count = Arc::new(AtomicU64::new(0));
    let zone_latency_total_ns = Arc::new(AtomicU64::new(0));
    let zone_latency_max_ns = Arc::new(AtomicU64::new(0));
    let zone_wait_total_ns = Arc::new(AtomicU64::new(0));
    let zone_wait_max_ns = Arc::new(AtomicU64::new(0));
    let zone_wait_deferred_only_total_ns = Arc::new(AtomicU64::new(0));
    let zone_wait_deferred_only_count = Arc::new(AtomicU64::new(0));
    let scheduler_pick_count = Arc::new(AtomicU64::new(0));
    let scheduler_wait_total_ns = Arc::new(AtomicU64::new(0));
    let scheduler_wait_max_ns = Arc::new(AtomicU64::new(0));
    let total_heartbeats_sent = Arc::new(AtomicU64::new(0));
    let timeout_detections = Arc::new(AtomicU64::new(0));
    let per_robot_completed: Arc<Vec<AtomicU64>> =
        Arc::new((0..cfg.robots).map(|_| AtomicU64::new(0)).collect());
    let zones = Arc::new(vec![
        "Intensive Care Unit-A".to_string(),
        "Operating Room-1".to_string(),
        "Ward-1".to_string(),
        "Pharmacy".to_string(),
    ]);

    println!(
        "Start simulation: robots={}, duration={}s, heartbeat={}ms, task_gen={}ms, timeout={}ms, lookahead={}",
        cfg.robots,
        cfg.duration_secs,
        cfg.heartbeat_ms,
        cfg.task_gen_ms,
        cfg.timeout_ms,
        cfg.lookahead
    );
    println!("Random generator seed     : {}", initial_seed);
    
    // 2) Start task generator as an external process and consume tasks via pipe.
    let taskgen_process = taskgen_process::spawn_taskgen_process(
        &cfg,
        initial_seed,
        Arc::clone(&zones),
        Arc::clone(&task_queue),
        Arc::clone(&running),
        Arc::clone(&generated_tasks),
        Arc::clone(&peak_queue_length),
        Arc::clone(&workload_fingerprint),
    );

    // 3) Start monitor output thread for online/offline visibility.
    let monitor_handle = {
        let health_monitor = Arc::clone(&health_monitor);
        let running = Arc::clone(&running);
        let timeout_detections = Arc::clone(&timeout_detections);
        thread::spawn(move || {
            let mut prev_offline = HashSet::new();
            while running.load(Ordering::SeqCst) {
                let online = health_monitor.get_online_robots();
                let offline = health_monitor.get_offline_robots();
                let current_offline: HashSet<u64> = offline.iter().copied().collect();
                for _ in current_offline.difference(&prev_offline) {
                    timeout_detections.fetch_add(1, Ordering::SeqCst);
                }
                prev_offline = current_offline;
                println!("[Health] online={online:?}, offline={offline:?}");
                thread::sleep(Duration::from_secs(2));
            }
        })
    };

    // 4) Spawn robot workers; each robot sends heartbeats and processes tasks.
    let mut robot_handles = Vec::new();
    for robot_id in 1..=cfg.robots as u64 {
        let task_queue = Arc::clone(&task_queue);
        let zone_manager = Arc::clone(&zone_manager);
        let health_monitor = Arc::clone(&health_monitor);
        let running = Arc::clone(&running);
        let zones = Arc::clone(&zones);
        let completed_tasks = Arc::clone(&completed_tasks);
        let completed_emergency_tasks = Arc::clone(&completed_emergency_tasks);
        let zone_contention_count = Arc::clone(&zone_contention_count);
        let scheduler_deferral_count = Arc::clone(&scheduler_deferral_count);
        let zone_lock_deferral_count = Arc::clone(&zone_lock_deferral_count);
        let zone_acquire_success_count = Arc::clone(&zone_acquire_success_count);
        let zone_latency_total_ns = Arc::clone(&zone_latency_total_ns);
        let zone_latency_max_ns = Arc::clone(&zone_latency_max_ns);
        let zone_wait_total_ns = Arc::clone(&zone_wait_total_ns);
        let zone_wait_max_ns = Arc::clone(&zone_wait_max_ns);
        let zone_wait_deferred_only_total_ns = Arc::clone(&zone_wait_deferred_only_total_ns);
        let zone_wait_deferred_only_count = Arc::clone(&zone_wait_deferred_only_count);
        let scheduler_pick_count = Arc::clone(&scheduler_pick_count);
        let scheduler_wait_total_ns = Arc::clone(&scheduler_wait_total_ns);
        let scheduler_wait_max_ns = Arc::clone(&scheduler_wait_max_ns);
        let total_heartbeats_sent = Arc::clone(&total_heartbeats_sent);
        let per_robot_completed = Arc::clone(&per_robot_completed);

        // Robot 1 intentionally fails midway to demonstrate timeout/offline behavior.
        let crash_after = if robot_id == 1 {
            Some(Duration::from_secs((cfg.duration_secs / 2).max(3)))
        } else {
            None
        };

        robot_handles.push(thread::spawn(move || {
            let start = Instant::now();
            let mut last_heartbeat = Instant::now() - Duration::from_secs(1);
            let zone_count = zones.len().max(1);
            let mut zone_cursor = (robot_id as usize) % zone_count;
            let mut scheduler_wait_started_at: Option<Instant> = None;

            while running.load(Ordering::SeqCst) {
                if let Some(crash_after) = crash_after {
                    if start.elapsed() >= crash_after {
                        println!("[Robot {robot_id}] simulated failure: stop sending heartbeat");
                        break;
                    }
                }

                if last_heartbeat.elapsed() >= Duration::from_millis(cfg.heartbeat_ms) {
                    health_monitor.send_heartbeat(robot_id);
                    total_heartbeats_sent.fetch_add(1, Ordering::SeqCst);
                    last_heartbeat = Instant::now();
                }

                let robot_position =
                    blaze_core::Position::new((robot_id % 10) as f64, ((robot_id * 3) % 10) as f64);

                let occupied = zone_manager.occupied_zones_snapshot();

                let mut preferred_zones = Vec::with_capacity(zone_count);
                for offset in 0..zone_count {
                    let idx = (zone_cursor + offset) % zone_count;
                    let zone = &zones[idx];
                    if !occupied.contains(zone) {
                        preferred_zones.push(zone.as_str());
                    }
                }
                zone_cursor = (zone_cursor + 1) % zone_count;

                if preferred_zones.is_empty() {
                    scheduler_deferral_count.fetch_add(1, Ordering::SeqCst);
                    scheduler_wait_started_at.get_or_insert_with(Instant::now);
                    thread::sleep(Duration::from_millis(20));
                    continue;
                }

                let Some(task) = task_queue
                    .fetch_task_from_zones_wait_for_robot(
                        &preferred_zones,
                        robot_position,
                        Duration::from_millis(250),
                    )
                else {
                    scheduler_deferral_count.fetch_add(1, Ordering::SeqCst);
                    scheduler_wait_started_at.get_or_insert_with(Instant::now);
                    thread::sleep(Duration::from_millis(50));
                    continue;
                };

                let scheduler_wait_ns = scheduler_wait_started_at
                    .take()
                    .map(|t| t.elapsed().as_nanos() as u64)
                    .unwrap_or(0);
                scheduler_pick_count.fetch_add(1, Ordering::SeqCst);
                scheduler_wait_total_ns.fetch_add(scheduler_wait_ns, Ordering::SeqCst);
                update_atomic_max(&scheduler_wait_max_ns, scheduler_wait_ns);

                let task_fetch_time = Instant::now();
                let mut had_task_deferral = false;
                let mut wait_ns = 0u64;

                // Per-task waiting: from fetch to successful zone acquire.
                loop {
                    let acquire_start = Instant::now();
                    if zone_manager.acquire_zone(&task.zone) {
                        wait_ns = acquire_start.elapsed().as_nanos() as u64;
                        break;
                    }

                    zone_contention_count.fetch_add(1, Ordering::SeqCst);
                    zone_lock_deferral_count.fetch_add(1, Ordering::SeqCst);
                    had_task_deferral = true;
                    thread::sleep(Duration::from_millis(15));

                    if !running.load(Ordering::SeqCst) {
                        break;
                    }
                }

                if !running.load(Ordering::SeqCst) {
                    break;
                }

                if !running.load(Ordering::SeqCst) {
                    zone_manager.release_zone(&task.zone);
                    break;
                }

                let end_to_end_wait_ns = task_fetch_time.elapsed().as_nanos() as u64;
                zone_acquire_success_count.fetch_add(1, Ordering::SeqCst);
                zone_latency_total_ns.fetch_add(wait_ns, Ordering::SeqCst);
                update_atomic_max(&zone_latency_max_ns, wait_ns);
                zone_wait_total_ns.fetch_add(end_to_end_wait_ns, Ordering::SeqCst);
                update_atomic_max(&zone_wait_max_ns, end_to_end_wait_ns);
                if had_task_deferral {
                    zone_wait_deferred_only_total_ns.fetch_add(end_to_end_wait_ns, Ordering::SeqCst);
                    zone_wait_deferred_only_count.fetch_add(1, Ordering::SeqCst);
                }

                println!(
                    "[Robot {robot_id}] acquired zone {}, execute task #{} (emergency={},) for {}ms",
                    task.zone, task.id, task.is_emergency, task.execution_ms
                );
                thread::sleep(Duration::from_millis(task.execution_ms));
                zone_manager.release_zone(&task.zone);
                completed_tasks.fetch_add(1, Ordering::SeqCst);
                if task.is_emergency {
                    completed_emergency_tasks.fetch_add(1, Ordering::SeqCst);
                }
                let robot_idx = (robot_id as usize).saturating_sub(1);
                per_robot_completed[robot_idx].fetch_add(1, Ordering::SeqCst);
                println!(
                    "[Robot {robot_id}] released zone {}, completed task #{}",
                    task.zone, task.id
                );
            }

            println!("[Robot {robot_id}] stopped");
        }));
    }

    // 5) Let simulation run for the configured duration, then stop and join all threads.
    thread::sleep(Duration::from_secs(cfg.duration_secs));
    running.store(false, Ordering::SeqCst);

    for h in robot_handles {
        let _ = h.join();
    }
    taskgen_process.stop();
    let _ = monitor_handle.join();

    let elapsed_secs = simulation_start.elapsed().as_secs_f64();
    let generated = generated_tasks.load(Ordering::SeqCst);
    let completed = completed_tasks.load(Ordering::SeqCst);
    let completed_emergency = completed_emergency_tasks.load(Ordering::SeqCst);
    let fingerprint = workload_fingerprint.load(Ordering::SeqCst);
    let peak_queue = peak_queue_length.load(Ordering::SeqCst);
    let contention = zone_contention_count.load(Ordering::SeqCst);
    let scheduler_deferrals = scheduler_deferral_count.load(Ordering::SeqCst);
    let zone_lock_deferrals = zone_lock_deferral_count.load(Ordering::SeqCst);
    let zone_acquire_success = zone_acquire_success_count.load(Ordering::SeqCst);
    let zone_latency_total = zone_latency_total_ns.load(Ordering::SeqCst);
    let zone_latency_max = zone_latency_max_ns.load(Ordering::SeqCst);
    let zone_wait_total = zone_wait_total_ns.load(Ordering::SeqCst);
    let zone_wait_max = zone_wait_max_ns.load(Ordering::SeqCst);
    let zone_wait_deferred_only_total = zone_wait_deferred_only_total_ns.load(Ordering::SeqCst);
    let zone_wait_deferred_only_count = zone_wait_deferred_only_count.load(Ordering::SeqCst);
    let scheduler_picks = scheduler_pick_count.load(Ordering::SeqCst);
    let scheduler_wait_total = scheduler_wait_total_ns.load(Ordering::SeqCst);
    let scheduler_wait_max = scheduler_wait_max_ns.load(Ordering::SeqCst);
    let heartbeats = total_heartbeats_sent.load(Ordering::SeqCst);
    let timeout_count = timeout_detections.load(Ordering::SeqCst);
    let remaining = task_queue.len() as u64;
    let online_robots = health_monitor.get_online_robots();
    let offline_robots = health_monitor.get_offline_robots();
    let per_robot_completion_snapshot: Vec<u64> =
        per_robot_completed.iter().map(|x| x.load(Ordering::SeqCst)).collect();

    let summary = SummaryData {
        configured_robots: cfg.robots,
        elapsed_secs,
        generated,
        completed,
        completed_emergency,
        remaining,
        zone_contention: contention,
        scheduler_deferrals,
        zone_lock_deferrals,
        scheduler_pick_count: scheduler_picks,
        scheduler_wait_total_ns: scheduler_wait_total,
        scheduler_wait_max_ns: scheduler_wait_max,
        zone_acquire_success,
        zone_latency_total_ns: zone_latency_total,
        zone_latency_max_ns: zone_latency_max,
        zone_wait_total_ns: zone_wait_total,
        zone_wait_max_ns: zone_wait_max,
        zone_wait_deferred_only_total_ns: zone_wait_deferred_only_total,
        zone_wait_deferred_only_count,
        peak_queue,
        workload_fingerprint: fingerprint,
        total_heartbeats_sent: heartbeats,
        timeout_detections: timeout_count,
        online_robots,
        offline_robots,
        per_robot_completed: per_robot_completion_snapshot,
    };
    print_summary(&summary);

    health_monitor.stop_checker_thread();

    println!(
        "Simulation ended. Remaining tasks in queue: {}",
        task_queue.len()
    );
}
