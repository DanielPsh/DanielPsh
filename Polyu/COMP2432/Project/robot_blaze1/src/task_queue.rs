use std::collections::VecDeque;
use std::sync::{Mutex, Condvar};

use crate::task::Task;

pub struct TaskQueue {
    queue: Mutex<VecDeque<Task>>,
    condvar: Condvar,
}

impl TaskQueue {

    pub fn new() -> Self {
        TaskQueue {
            queue: Mutex::new(VecDeque::new()),
            condvar: Condvar::new(),
        }
    }

    pub fn add_task(&self, task: Task) {
        let mut q = self.queue.lock().unwrap();
        q.push_back(task);
        self.condvar.notify_one();
    }

    pub fn get_task(&self) -> Task {
        let mut q = self.queue.lock().unwrap();

        while q.is_empty() {
            q = self.condvar.wait(q).unwrap();
        }

        q.pop_front().unwrap()
    }

}