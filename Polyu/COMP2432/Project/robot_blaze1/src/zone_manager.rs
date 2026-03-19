use std::collections::HashSet;
use std::sync::Mutex;

pub struct ZoneManager {
    zones: Mutex<HashSet<u32>>,
}

impl ZoneManager {

    pub fn new() -> Self {
        ZoneManager {
            zones: Mutex::new(HashSet::new()),
        }
    }

    pub fn enter_zone(&self, zone: u32) -> bool {

        let mut z = self.zones.lock().unwrap();

        if z.contains(&zone) {
            return false;
        }

        z.insert(zone);
        true
    }

    pub fn leave_zone(&self, zone: u32) {

        let mut z = self.zones.lock().unwrap();
        z.remove(&zone);

    }

}