use crate::config::Config;
use crate::metrics::update_atomic_max;
use crate::random::task_signature;
use blaze_core::{Position, Task};
use serde::{Deserialize, Serialize};
use std::io::{BufRead, BufReader, Write};
use std::process::{Child, Command, Stdio};
use std::sync::atomic::{AtomicBool, AtomicU64, Ordering};
use std::sync::Arc;
use std::thread::{self, JoinHandle};
use task_queue::TaskQueue;

#[derive(Debug, Serialize)]
struct BootstrapConfig {
    seed: u64,
    task_gen_ms: u64,
    zones: Vec<String>,
}

#[derive(Debug, Deserialize)]
struct TaskMessage {
    id: u64,
    x: f64,
    y: f64,
    zone: String,
    description: String,
    is_emergency: bool,
    execution_ms: u64,
}

pub struct TaskGenProcess {
    child: Child,
    reader_handle: JoinHandle<()>,
}

impl TaskGenProcess {
    /// Stops the task generator child process and waits for its reader thread to exit.
    pub fn stop(mut self) {
        let _ = self.child.kill();
        let _ = self.child.wait();
        let _ = self.reader_handle.join();
    }
}

/// Spawns the task generator as a child process and starts a reader thread
/// that forwards JSON task messages into the shared `TaskQueue`.
///
/// The child process runs the same app executable in `--taskgen-child` mode.
pub fn spawn_taskgen_process(
    cfg: &Config,
    seed: u64,
    zones: Arc<Vec<String>>,
    task_queue: Arc<TaskQueue>,
    running: Arc<AtomicBool>,
    generated_tasks: Arc<AtomicU64>,
    peak_queue_length: Arc<AtomicU64>,
    workload_fingerprint: Arc<AtomicU64>,
) -> TaskGenProcess {
    let app_exe = std::env::current_exe().expect("resolve current executable for taskgen child");

    let mut child = Command::new(app_exe)
        .arg("--taskgen-child")
        .stdin(Stdio::piped())
        .stdout(Stdio::piped())
        .stderr(Stdio::inherit())
        .spawn()
        .expect("Failed to spawn taskgen process");

    let bootstrap = BootstrapConfig {
        seed,
        task_gen_ms: cfg.task_gen_ms,
        zones: zones.as_ref().clone(),
    };

    if let Some(mut stdin) = child.stdin.take() {
        let payload = serde_json::to_string(&bootstrap).expect("serialize bootstrap config");
        writeln!(stdin, "{payload}").expect("write bootstrap config to taskgen process");
    }

    let stdout = child
        .stdout
        .take()
        .expect("taskgen process stdout should be piped");

    let reader_handle = thread::spawn(move || {
        let reader = BufReader::new(stdout);
        for line in reader.lines() {
            if !running.load(Ordering::SeqCst) {
                break;
            }

            let Ok(raw) = line else {
                break;
            };

            let Ok(msg) = serde_json::from_str::<TaskMessage>(&raw) else {
                continue;
            };

            let task = Task::new(
                msg.id,
                Position::new(msg.x, msg.y),
                msg.zone,
                msg.description,
                msg.is_emergency,
                msg.execution_ms,
            );

            let zone = task.zone.clone();
            let is_emergency = task.is_emergency;
            let x = task.position.x;
            let y = task.position.y;
            let sig = task_signature(&task);

            task_queue.push_task(task);
            generated_tasks.fetch_add(1, Ordering::SeqCst);
            let prev = workload_fingerprint.load(Ordering::SeqCst);
            let mixed = prev.rotate_left(5) ^ sig.wrapping_mul(0x9E3779B97F4A7C15);
            workload_fingerprint.store(mixed, Ordering::SeqCst);
            update_atomic_max(&peak_queue_length, task_queue.len() as u64);
            println!(
                "[TaskGen] pushed task #{} zone={} emergency={} pos=({:.2},{:.2})",
                msg.id, zone, is_emergency, x, y
            );
        }
        println!("[TaskGen] stopped");
    });

    TaskGenProcess {
        child,
        reader_handle,
    }
}
