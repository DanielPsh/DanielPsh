use std::collections::HashMap;
use std::sync::Mutex;
use std::time::{Instant, Duration};

pub struct HealthMonitor {
    robots: Mutex<HashMap<u32, Instant>>,
}

impl HealthMonitor {

    pub fn new() -> Self {
        HealthMonitor {
            robots: Mutex::new(HashMap::new()),
        }
    }

    pub fn heartbeat(&self, robot_id: u32) {

        let mut r = self.robots.lock().unwrap();
        r.insert(robot_id, Instant::now());

    }

    pub fn check_health(&self) {

        let mut r = self.robots.lock().unwrap();
        let now = Instant::now();

        for (id, last) in r.iter() {

            if now.duration_since(*last) > Duration::from_secs(5) {
                println!("Robot {} is OFFLINE", id);
            }

        }

    }

}