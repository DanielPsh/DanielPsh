// Role B
/*
1. task-queue crate

Create TaskQueue struct in crates/task-queue/src/lib.rs
Must support:
push_task(task: Task)
fetch_task() -> Option<Task> (basic version)
Optional: fetch_nearest_task(robot_position) -> Option<Task>

Choose one main implementation (recommended: mpsc::channel for simplicity, or Arc<Mutex<VecDeque<Task>>> + Condvar)
Make it fully thread-safe and lock-free where possible

2. Benchmark (primitives comparison)

Use criterion crate to measure performance
Compare at least 3 versions:
Channel-based queue
Mutex<VecDeque> version
RwLock version (if applicable)

Measure:
Task throughput (tasks per second)
Average fetch latency
CPU usage under high load (10 / 50 / 100 robots)

Generate tables and graphs for the report

Goal: Show why you chose a particular primitive (this is worth a lot of marks in Synchronization 40%)
*/

use blaze_core::{Position, Task};
use std::collections::{HashMap, VecDeque};
use std::sync::{Condvar, Mutex};
use std::time::{Duration, Instant};

const DEFAULT_NON_EMERGENCY_LOOKAHEAD: usize = 3;
const DEFAULT_EMERGENCY_LOOKAHEAD: usize = 8;

#[derive(Debug, Default)]
struct QueueBuckets {
    emergency: HashMap<String, VecDeque<Task>>,
    normal: HashMap<String, VecDeque<Task>>,
    total_len: usize,
}

impl QueueBuckets {
    fn zone_names(&self) -> Vec<String> {
        let mut names = Vec::with_capacity(self.emergency.len() + self.normal.len());
        for k in self.emergency.keys() {
            if !names.iter().any(|z| z == k) {
                names.push(k.clone());
            }
        }
        for k in self.normal.keys() {
            if !names.iter().any(|z| z == k) {
                names.push(k.clone());
            }
        }
        names
    }

    fn dec_len(&mut self) {
        self.total_len = self.total_len.saturating_sub(1);
    }

    fn remove_empty_zone(map: &mut HashMap<String, VecDeque<Task>>, zone: &str) {
        if map.get(zone).is_some_and(VecDeque::is_empty) {
            map.remove(zone);
        }
    }

    fn pop_any_task(&mut self) -> Option<Task> {
        if let Some(task) = Self::pop_any_from_map(&mut self.emergency) {
            self.dec_len();
            return Some(task);
        }
        if let Some(task) = Self::pop_any_from_map(&mut self.normal) {
            self.dec_len();
            return Some(task);
        }
        None
    }

    fn pop_any_from_map(map: &mut HashMap<String, VecDeque<Task>>) -> Option<Task> {
        let zone = map.keys().next().cloned()?;
        let task = map.get_mut(&zone).and_then(VecDeque::pop_front);
        Self::remove_empty_zone(map, &zone);
        task
    }

    fn pop_best_from_map(
        map: &mut HashMap<String, VecDeque<Task>>,
        zone_order: &[String],
        robot_position: Position,
        lookahead: usize,
    ) -> Option<Task> {
        let mut best: Option<(String, usize, f64)> = None;

        for zone in zone_order {
            let Some(q) = map.get(zone) else {
                continue;
            };

            for (idx, task) in q.iter().enumerate().take(lookahead) {
                let d = task.position.distance_to(&robot_position);
                match best {
                    None => best = Some((zone.clone(), idx, d)),
                    Some((_, _, best_d)) if d < best_d => best = Some((zone.clone(), idx, d)),
                    _ => {}
                }
            }
        }

        let (zone, idx, _) = best?;
        let task = map.get_mut(&zone).and_then(|q| q.remove(idx));
        Self::remove_empty_zone(map, &zone);
        task
    }

    fn pop_scheduled(
        &mut self,
        candidate_zones: Option<&[String]>,
        robot_position: Position,
        non_emergency_lookahead: usize,
    ) -> Option<Task> {
        let zones = match candidate_zones {
            Some(z) if !z.is_empty() => z.to_vec(),
            _ => self.zone_names(),
        };

        if zones.is_empty() {
            return None;
        }

        if let Some(task) = Self::pop_best_from_map(
            &mut self.emergency,
            &zones,
            robot_position,
            DEFAULT_EMERGENCY_LOOKAHEAD,
        ) {
            self.dec_len();
            return Some(task);
        }

        if let Some(task) = Self::pop_best_from_map(
            &mut self.normal,
            &zones,
            robot_position,
            non_emergency_lookahead,
        ) {
            self.dec_len();
            return Some(task);
        }

        None
    }
}

#[derive(Debug, Default)]
pub struct TaskQueue {
    buckets: Mutex<QueueBuckets>,
    cv: Condvar,
    non_emergency_lookahead: usize,
}

impl TaskQueue {
    /// Creates a task queue with the default non-emergency lookahead.
    pub fn new() -> Self {
        Self::new_with_lookahead(DEFAULT_NON_EMERGENCY_LOOKAHEAD)
    }

    /// Creates a task queue with a custom non-emergency lookahead window.
    pub fn new_with_lookahead(non_emergency_lookahead: usize) -> Self {
        Self {
            buckets: Mutex::new(QueueBuckets::default()),
            cv: Condvar::new(),
            non_emergency_lookahead: non_emergency_lookahead.max(1),
        }
    }

    /// Pushes a task into the queue and notifies one waiting consumer.
    pub fn push_task(&self, task: Task) {
        let mut buckets = self.buckets.lock().expect("task queue mutex poisoned");
        let zone = task.zone.clone();
        if task.is_emergency {
            buckets.emergency.entry(zone).or_default().push_back(task);
        } else {
            buckets.normal.entry(zone).or_default().push_back(task);
        }
        buckets.total_len += 1;
        self.cv.notify_one();
    }

    /// Fetches one available task without waiting.
    /// Emergency tasks are preferred over normal tasks.
    pub fn fetch_task(&self) -> Option<Task> {
        let mut buckets = self.buckets.lock().expect("task queue mutex poisoned");
        buckets.pop_any_task()
    }

    /// Fetches one task, waiting up to `timeout` if the queue is empty.
    pub fn fetch_task_wait(&self, timeout: Duration) -> Option<Task> {
        let start = Instant::now();
        let mut buckets = self.buckets.lock().expect("task queue mutex poisoned");

        loop {
            if let Some(task) = buckets.pop_any_task() {
                return Some(task);
            }

            let elapsed = start.elapsed();
            if elapsed >= timeout {
                return None;
            }

            let remaining = timeout.saturating_sub(elapsed);
            let (guard, wait_result) = self
                .cv
                .wait_timeout(buckets, remaining)
                .expect("task queue condvar wait poisoned");
            buckets = guard;

            if wait_result.timed_out() && buckets.total_len == 0 {
                return None;
            }
        }
    }

    /// Fetches one task using robot-aware scheduling (nearest-first with emergency priority).
    pub fn fetch_task_for_robot(&self, robot_position: Position) -> Option<Task> {
        let mut buckets = self.buckets.lock().expect("task queue mutex poisoned");
        buckets.pop_scheduled(None, robot_position, self.non_emergency_lookahead)
    }

    /// Robot-aware fetch that waits up to `timeout`.
    pub fn fetch_task_wait_for_robot(
        &self,
        robot_position: Position,
        timeout: Duration,
    ) -> Option<Task> {
        let start = Instant::now();
        let mut buckets = self.buckets.lock().expect("task queue mutex poisoned");

        loop {
            if let Some(task) =
                buckets.pop_scheduled(None, robot_position, self.non_emergency_lookahead)
            {
                return Some(task);
            }

            let elapsed = start.elapsed();
            if elapsed >= timeout {
                return None;
            }

            let remaining = timeout.saturating_sub(elapsed);
            let (guard, wait_result) = self
                .cv
                .wait_timeout(buckets, remaining)
                .expect("task queue condvar wait poisoned");
            buckets = guard;

            if wait_result.timed_out() && buckets.total_len == 0 {
                return None;
            }
        }
    }

    /// Zone-targeted robot-aware fetch with timeout.
    ///
    /// The queue only considers tasks in `zones_in_order`, then picks nearest
    /// emergency first, followed by nearest non-emergency within lookahead.
    pub fn fetch_task_from_zones_wait_for_robot(
        &self,
        zones_in_order: &[&str],
        robot_position: Position,
        timeout: Duration,
    ) -> Option<Task> {
        let start = Instant::now();
        let candidates: Vec<String> = zones_in_order.iter().map(|z| (*z).to_string()).collect();
        let mut buckets = self.buckets.lock().expect("task queue mutex poisoned");

        loop {
            if let Some(task) = buckets.pop_scheduled(
                Some(&candidates),
                robot_position,
                self.non_emergency_lookahead,
            ) {
                return Some(task);
            }

            let elapsed = start.elapsed();
            if elapsed >= timeout {
                return None;
            }

            let remaining = timeout.saturating_sub(elapsed);
            let (guard, wait_result) = self
                .cv
                .wait_timeout(buckets, remaining)
                .expect("task queue condvar wait poisoned");
            buckets = guard;

            if wait_result.timed_out() {
                return None;
            }
        }
    }

    /// Zone-targeted fetch with timeout using a neutral position `(0,0)`.
    pub fn fetch_task_from_zones_wait(&self, zones_in_order: &[&str], timeout: Duration) -> Option<Task> {
        self.fetch_task_from_zones_wait_for_robot(
            zones_in_order,
            Position::new(0.0, 0.0),
            timeout,
        )
    }

    /// Alias of `fetch_task_for_robot` kept for readability at call sites.
    pub fn fetch_nearest_task(&self, robot_position: Position) -> Option<Task> {
        self.fetch_task_for_robot(robot_position)
    }

    /// Returns the current number of queued tasks.
    pub fn len(&self) -> usize {
        let buckets = self.buckets.lock().expect("task queue mutex poisoned");
        buckets.total_len
    }

    /// Returns `true` when the queue has no pending tasks.
    pub fn is_empty(&self) -> bool {
        self.len() == 0
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use std::collections::HashSet;
    use std::sync::atomic::{AtomicUsize, Ordering};
    use std::sync::Arc;
    use std::thread;

    fn make_task(id: u64, x: f64, zone: &str) -> Task {
        Task::new(
            id,
            Position::new(x, 0.0),
            zone,
            format!("task-{id}"),
            false,
            500,
        )
    }

    fn make_emergency_task(id: u64, x: f64, zone: &str) -> Task {
        Task::new(
            id,
            Position::new(x, 0.0),
            zone,
            format!("emergency-{id}"),
            true,
            500,
        )
    }

    #[test]
    fn emergency_is_prioritized() {
        let q = TaskQueue::new();
        q.push_task(make_task(1, 0.2, "z1"));
        q.push_task(make_emergency_task(2, 10.0, "z1"));

        let first = q.fetch_task_for_robot(Position::new(0.0, 0.0)).unwrap();
        assert!(first.is_emergency);
        assert_eq!(first.id, 2);
    }

    #[test]
    fn nearest_emergency_is_selected() {
        let q = TaskQueue::new();
        q.push_task(make_emergency_task(10, 9.0, "z1"));
        q.push_task(make_emergency_task(20, 1.0, "z2"));

        let selected = q.fetch_task_for_robot(Position::new(0.0, 0.0)).unwrap();
        assert_eq!(selected.id, 20);
    }

    #[test]
    fn zone_targeted_fetch_only_uses_given_zones() {
        let q = TaskQueue::new();
        q.push_task(make_task(1, 1.0, "z1"));
        q.push_task(make_task(2, 1.0, "z2"));

        let t = q
            .fetch_task_from_zones_wait(&["z2"], Duration::from_millis(10))
            .unwrap();
        assert_eq!(t.zone, "z2");
        assert_eq!(q.len(), 1);
    }

    #[test]
    fn lookahead_affects_non_emergency_choice() {
        let q = TaskQueue::new_with_lookahead(1);
        q.push_task(make_task(1, 100.0, "z1"));
        q.push_task(make_task(2, 1.0, "z1"));

        // lookahead=1 behaves like FIFO for normal tasks in same zone.
        let first = q.fetch_task_for_robot(Position::new(0.0, 0.0)).unwrap();
        assert_eq!(first.id, 1);
    }

    #[test]
    fn wait_timeout_returns_none_when_empty() {
        let q = TaskQueue::new();
        assert!(q.fetch_task_wait(Duration::from_millis(20)).is_none());
    }

    #[test]
    fn concurrent_push_and_fetch_no_duplicates() {
        let q = Arc::new(TaskQueue::new());
        let producer_count = 6;
        let tasks_per_producer = 150;
        let total = producer_count * tasks_per_producer;

        let mut producers = Vec::new();
        for p in 0..producer_count {
            let q = Arc::clone(&q);
            producers.push(thread::spawn(move || {
                for i in 0..tasks_per_producer {
                    let id = (p * tasks_per_producer + i) as u64;
                    q.push_task(make_task(id, id as f64, if i % 2 == 0 { "z1" } else { "z2" }));
                }
            }));
        }

        let consumed = Arc::new(Mutex::new(HashSet::new()));
        let consumed_count = Arc::new(AtomicUsize::new(0));
        let mut consumers = Vec::new();
        for _ in 0..4 {
            let q = Arc::clone(&q);
            let consumed = Arc::clone(&consumed);
            let consumed_count = Arc::clone(&consumed_count);
            consumers.push(thread::spawn(move || loop {
                if consumed_count.load(Ordering::SeqCst) >= total {
                    break;
                }
                if let Some(task) = q.fetch_task_wait(Duration::from_millis(20)) {
                    let mut set = consumed.lock().expect("consumed mutex poisoned");
                    assert!(set.insert(task.id), "duplicate task id fetched");
                    consumed_count.fetch_add(1, Ordering::SeqCst);
                }
            }));
        }

        for h in producers {
            h.join().expect("producer thread panicked");
        }
        for h in consumers {
            h.join().expect("consumer thread panicked");
        }

        assert!(q.is_empty());
        assert_eq!(consumed.lock().unwrap().len(), total);
    }
}
