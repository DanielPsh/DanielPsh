use robot_blaze::task::Task;
use robot_blaze::task_queue::TaskQueue;

#[test]
fn test_add_task() {

    let queue = TaskQueue::new();

    queue.add_task(Task::new(1, "Test Task", 1));

    let task = queue.get_task();

    assert_eq!(task.id, 1);
    assert_eq!(task.description, "Test Task");
    assert_eq!(task.zone, 1);
}

#[test]
fn test_multiple_tasks() {

    let queue = TaskQueue::new();

    queue.add_task(Task::new(1, "Task A", 1));
    queue.add_task(Task::new(2, "Task B", 2));

    let task1 = queue.get_task();
    let task2 = queue.get_task();

    assert_eq!(task1.id, 1);
    assert_eq!(task2.id, 2);
}