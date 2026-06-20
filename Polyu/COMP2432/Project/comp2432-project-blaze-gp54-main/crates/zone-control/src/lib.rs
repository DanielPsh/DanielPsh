// Role C
/*
1. zone-control crate

Create ZoneManager struct
Methods:
acquire_zone(zone: &str) -> bool (returns true if lock acquired)
release_zone(zone: &str)
is_occupied(zone: &str) -> bool

Use HashMap<String, Mutex<()>> or HashMap<String, RwLock<()>>
Prevent two robots from entering the same zone at the same time

2. testing

Write comprehensive tests in crates/zone-control/tests/ and inside lib.rs
Basic: single robot acquire/release
Concurrent: 20 robots trying to enter same zone at the same time
Stress: 100 robots, 10,000 operations, check no race condition or deadlock

Use std::thread::spawn + join to simulate real concurrency
Add #[test] / #[cfg(test)] that must pass with cargo test -p zone-control / cargo test

Goal: Prove mutual exclusion works perfectly (very important for Synchronization 40%)
*/

use std::collections::HashSet;
use std::sync::Mutex;

#[derive(Debug, Default)]
pub struct ZoneManager {
    occupied: Mutex<HashSet<String>>,
}

impl ZoneManager {
    pub fn new() -> Self {
        Self {
            occupied: Mutex::new(HashSet::new()),
        }
    }

    /// Try to acquire a zone.
    /// Returns true if the zone was successfully acquired.
    /// Returns false if the zone is already occupied.
    ///
    /// Deadlock prevention note:
    /// this method is non-blocking and only grants one zone at a time.
    /// Workers do not wait while holding a zone lock, which removes
    /// circular-wait/hold-and-wait deadlock conditions.
    pub fn acquire_zone(&self, zone: &str) -> bool {
        let mut occupied = self.occupied.lock().unwrap();

        if occupied.contains(zone) {
            false
        } else {
            occupied.insert(zone.to_string());
            true
        }
    }

    /// Release a zone.
    /// If the zone is not occupied, this does nothing.
    pub fn release_zone(&self, zone: &str) {
        let mut occupied = self.occupied.lock().unwrap();
        occupied.remove(zone);
    }

    /// Check whether a zone is currently occupied.
    pub fn is_occupied(&self, zone: &str) -> bool {
        let occupied = self.occupied.lock().unwrap();
        occupied.contains(zone)
    }

    /// Return the number of currently occupied zones.
    pub fn occupied_count(&self) -> usize {
        let occupied = self.occupied.lock().unwrap();
        occupied.len()
    }

    /// Snapshot currently occupied zones.
    /// This is intended for scheduling optimization only.
    pub fn occupied_zones_snapshot(&self) -> HashSet<String> {
        let occupied = self.occupied.lock().unwrap();
        occupied.clone()
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use std::sync::{Arc, Barrier};
    use std::thread;

    #[test]
    fn single_robot_acquire_and_release() {
        let manager = ZoneManager::new();

        assert!(!manager.is_occupied("ZoneA"));

        assert!(manager.acquire_zone("ZoneA"));
        assert!(manager.is_occupied("ZoneA"));
        assert_eq!(manager.occupied_count(), 1);

        manager.release_zone("ZoneA");
        assert!(!manager.is_occupied("ZoneA"));
        assert_eq!(manager.occupied_count(), 0);
    }

    #[test]
    fn second_robot_cannot_acquire_same_zone() {
        let manager = ZoneManager::new();

        assert!(manager.acquire_zone("ZoneA"));
        assert!(!manager.acquire_zone("ZoneA"));

        assert!(manager.is_occupied("ZoneA"));
        assert_eq!(manager.occupied_count(), 1);
    }

    #[test]
    fn different_zones_can_be_acquired() {
        let manager = ZoneManager::new();

        assert!(manager.acquire_zone("ZoneA"));
        assert!(manager.acquire_zone("ZoneB"));
        assert!(manager.acquire_zone("ZoneC"));

        assert!(manager.is_occupied("ZoneA"));
        assert!(manager.is_occupied("ZoneB"));
        assert!(manager.is_occupied("ZoneC"));
        assert_eq!(manager.occupied_count(), 3);
    }

    #[test]
    fn release_unoccupied_zone_is_safe() {
        let manager = ZoneManager::new();

        manager.release_zone("ZoneA");

        assert!(!manager.is_occupied("ZoneA"));
        assert_eq!(manager.occupied_count(), 0);
    }

    #[test]
    fn concurrent_20_robots_same_zone_only_one_succeeds() {
        let manager = Arc::new(ZoneManager::new());
        let barrier = Arc::new(Barrier::new(20));
        let mut handles = Vec::new();

        for _ in 0..20 {
            let manager = Arc::clone(&manager);
            let barrier = Arc::clone(&barrier);

            let handle = thread::spawn(move || {
                barrier.wait();
                manager.acquire_zone("OperatingRoom")
            });

            handles.push(handle);
        }

        let mut success_count = 0;
        for handle in handles {
            if handle.join().unwrap() {
                success_count += 1;
            }
        }

        assert_eq!(success_count, 1);
        assert!(manager.is_occupied("OperatingRoom"));
        assert_eq!(manager.occupied_count(), 1);
    }

    #[test]
    fn stress_test_many_threads_many_operations_no_panic() {
        let manager = Arc::new(ZoneManager::new());
        let zones: Vec<String> = (0..10).map(|i| format!("Zone{i}")).collect();

        let mut handles = Vec::new();

        for thread_id in 0..100 {
            let manager = Arc::clone(&manager);
            let zones = zones.clone();

            let handle = thread::spawn(move || {
                for op in 0..100 {
                    let zone = &zones[(thread_id + op) % zones.len()];

                    if manager.acquire_zone(zone) {
                        assert!(manager.is_occupied(zone));
                        manager.release_zone(zone);
                    }
                }
            });

            handles.push(handle);
        }

        for handle in handles {
            handle.join().unwrap();
        }

        assert_eq!(manager.occupied_count(), 0);

        for zone in &zones {
            assert!(!manager.is_occupied(zone));
        }
    }
}
