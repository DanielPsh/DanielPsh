// Role D
/*
1. health-monitor crate 

Create HealthMonitor struct
Methods:
send_heartbeat(robot_id: RobotId)
start_checker_thread() (background thread)
get_online_robots() -> Vec<RobotId>
is_online(robot_id) -> bool

Use Arc<RwLock<HashMap<RobotId, Instant>>> to store last heartbeat time
Background thread checks every 3–5 seconds and marks robots as Offline if timeout
Optional: re-queue tasks of offline robots
*/

use blaze_core::RobotId;
use std::collections::{HashMap, HashSet};
use std::sync::atomic::{AtomicBool, Ordering};
use std::sync::{Arc, Mutex, RwLock};
use std::thread::{self, JoinHandle};
use std::time::{Duration, Instant};

#[derive(Debug)]
pub struct HealthMonitor {
    heartbeats: Arc<RwLock<HashMap<RobotId, Instant>>>,
    offline: Arc<RwLock<HashSet<RobotId>>>,
    timeout: Duration,
    check_interval: Duration,
    running: Arc<AtomicBool>,
    checker_handle: Mutex<Option<JoinHandle<()>>>,
}

impl HealthMonitor {
    /// Creates a new health monitor with the given timeout and background check interval.
    pub fn new(timeout: Duration, check_interval: Duration) -> Self {
        Self {
            heartbeats: Arc::new(RwLock::new(HashMap::new())),
            offline: Arc::new(RwLock::new(HashSet::new())),
            timeout,
            check_interval,
            running: Arc::new(AtomicBool::new(false)),
            checker_handle: Mutex::new(None),
        }
    }

    /// Records the latest heartbeat timestamp for a robot.
    /// A heartbeat also marks the robot as online by removing it from the offline set.
    pub fn send_heartbeat(&self, robot_id: RobotId) {
        let now = Instant::now();

        {
            let mut hb = self.heartbeats.write().expect("heartbeats lock poisoned");
            hb.insert(robot_id, now);
        }

        {
            let mut off = self.offline.write().expect("offline lock poisoned");
            off.remove(&robot_id);
        }
    }

    /// Starts the background checker thread if it is not already running.
    /// The checker periodically marks robots as offline when they exceed the timeout.
    pub fn start_checker_thread(&self) {
        let mut handle_guard = self.checker_handle.lock().expect("checker_handle poisoned");
        if handle_guard.is_some() {
            return;
        }

        self.running.store(true, Ordering::SeqCst);

        let heartbeats = Arc::clone(&self.heartbeats);
        let offline = Arc::clone(&self.offline);
        let running = Arc::clone(&self.running);
        let timeout = self.timeout;
        let check_interval = self.check_interval;

        let h = thread::spawn(move || {
            while running.load(Ordering::SeqCst) {
                thread::sleep(check_interval);

                let now = Instant::now();

                let snapshot: Vec<(RobotId, Instant)> = {
                    let hb = heartbeats.read().expect("heartbeats lock poisoned");
                    hb.iter().map(|(id, t)| (*id, *t)).collect()
                };

                if !snapshot.is_empty() {
                    let mut off = offline.write().expect("offline lock poisoned");
                    for (id, last) in snapshot {
                        if now.duration_since(last) > timeout {
                            off.insert(id);
                        }
                    }
                }
            }
        });

        *handle_guard = Some(h);
    }

    /// Stops the background checker thread and waits for it to exit.
    /// This method is safe to call multiple times.
    pub fn stop_checker_thread(&self) {
        self.running.store(false, Ordering::SeqCst);

        let mut handle_guard = self.checker_handle.lock().expect("checker_handle poisoned");
        if let Some(h) = handle_guard.take() {
            let _ = h.join();
        }
    }

    /// Returns the list of robots that have a heartbeat record and are not considered offline.
    pub fn get_online_robots(&self) -> Vec<RobotId> {
        let now = Instant::now();

        let hb_snapshot: Vec<(RobotId, Instant)> = {
            let hb = self.heartbeats.read().expect("heartbeats lock poisoned");
            hb.iter().map(|(id, t)| (*id, *t)).collect()
        };

        let off_snapshot: HashSet<RobotId> = {
            let off = self.offline.read().expect("offline lock poisoned");
            off.iter().copied().collect()
        };

        hb_snapshot
            .into_iter()
            .filter(|(id, last)| {
                !off_snapshot.contains(id) && now.duration_since(*last) <= self.timeout
            })
            .map(|(id, _)| id)
            .collect()
    }

    /// Checks whether a robot is currently online based on the offline set and heartbeat timeout.
    pub fn is_online(&self, robot_id: RobotId) -> bool {
        {
            let off = self.offline.read().expect("offline lock poisoned");
            if off.contains(&robot_id) {
                return false;
            }
        }

        let last = {
            let hb = self.heartbeats.read().expect("heartbeats lock poisoned");
            hb.get(&robot_id).copied()
        };

        match last {
            None => false,
            Some(t) => Instant::now().duration_since(t) <= self.timeout,
        }
    }

    /// Returns the list of robots currently marked as offline by the background checker.
    pub fn get_offline_robots(&self) -> Vec<RobotId> {
        let off = self.offline.read().expect("offline lock poisoned");
        off.iter().copied().collect()
    }
}

impl Drop for HealthMonitor {
    fn drop(&mut self) {
        // Stops and joins the background checker thread to avoid leaking it on shutdown.
        self.running.store(false, Ordering::SeqCst);
        if let Ok(mut guard) = self.checker_handle.lock() {
            if let Some(h) = guard.take() {
                let _ = h.join();
            }
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use std::time::Duration;

    /// Verifies that a robot is marked offline after it stops sending heartbeats for longer than the timeout.
    /// This confirms the timeout detection and offline marking behavior of the checker thread.
    #[test]
    fn robot_times_out_and_becomes_offline() {
        let hm = HealthMonitor::new(Duration::from_millis(120), Duration::from_millis(20));
        hm.start_checker_thread();

        let id: RobotId = 1u64;
        hm.send_heartbeat(id);

        std::thread::sleep(Duration::from_millis(250));

        assert_eq!(hm.is_online(id), false);
        assert!(hm.get_offline_robots().contains(&id));

        hm.stop_checker_thread();
    }

    /// Verifies that regular heartbeats keep a robot online and prevent it from being marked offline.
    #[test]
    fn heartbeat_keeps_robot_online() {
        let hm = HealthMonitor::new(Duration::from_millis(200), Duration::from_millis(20));
        hm.start_checker_thread();

        let id: RobotId = 2u64;
        for _ in 0..5 {
            hm.send_heartbeat(id);
            std::thread::sleep(Duration::from_millis(50));
        }

        assert_eq!(hm.is_online(id), true);
        assert!(!hm.get_offline_robots().contains(&id));

        hm.stop_checker_thread();
    }

    /// Verifies that a robot can become online again after timing out by sending a new heartbeat.
    /// This matches the "heartbeat revives robot" behavior implemented in send_heartbeat().
    #[test]
    fn offline_robot_revives_on_heartbeat() {
        let hm = HealthMonitor::new(Duration::from_millis(100), Duration::from_millis(20));
        hm.start_checker_thread();

        let id: RobotId = 3u64;
        hm.send_heartbeat(id);

        std::thread::sleep(Duration::from_millis(220));
        assert_eq!(hm.is_online(id), false);

        hm.send_heartbeat(id);
        std::thread::sleep(Duration::from_millis(10));
        assert_eq!(hm.is_online(id), true);

        hm.stop_checker_thread();
    }
}