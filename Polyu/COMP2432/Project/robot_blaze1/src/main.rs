pub mod task;
pub mod task_queue;
pub mod zone_manager;
pub mod health_monitor;

use std::sync::Arc;
use std::thread;
use std::time::Duration;

use task::Task;
use task_queue::TaskQueue;
use zone_manager::ZoneManager;
use health_monitor::HealthMonitor;

fn main() {

    let queue = Arc::new(TaskQueue::new());
    let zones = Arc::new(ZoneManager::new());
    let health = Arc::new(HealthMonitor::new());

    queue.add_task(Task::new(1,"Deliver medicine",1));
    queue.add_task(Task::new(2,"Disinfect ICU",2));
    queue.add_task(Task::new(3,"Transport samples",1));

    let mut handles = vec![];

    for robot_id in 0..3 {

        let q = Arc::clone(&queue);
        let z = Arc::clone(&zones);
        let h = Arc::clone(&health);

        let handle = thread::spawn(move || {

            loop {

                let task = q.get_task();

                if z.enter_zone(task.zone) {

                    println!(
                        "Robot {} performing task {} in zone {}",
                        robot_id,
                        task.description,
                        task.zone
                    );

                    thread::sleep(Duration::from_secs(2));

                    z.leave_zone(task.zone);

                    h.heartbeat(robot_id);

                } else {

                    println!("Robot {} waiting for zone {}",robot_id,task.zone);
                    thread::sleep(Duration::from_secs(1));

                }

            }

        });

        handles.push(handle);
    }

    for _ in 0..5 {
        thread::sleep(Duration::from_secs(3));
        health.check_health();
    }

}