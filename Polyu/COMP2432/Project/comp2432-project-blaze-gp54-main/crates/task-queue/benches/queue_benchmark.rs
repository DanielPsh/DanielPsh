use criterion::{criterion_group, criterion_main, Criterion};
use task_queue::TaskQueue;
use blaze_core::{Task, Position};

use std::collections::VecDeque;
use std::sync::{Arc, RwLock, mpsc};

// ---------- Helper ----------
fn generate_tasks() -> Vec<Task> {
    (0..1000)
        .map(|i| Task {
            id: i,
            description: format!("Task {}", i),
            position: Position { x: i as f64, y: i as f64 },
            execution_ms: 100,
            zone: format!("Zone {}", i % 5),
            is_emergency: false,
        })
        .collect()
}

// ---------- 1. Your Queue ----------
fn benchmark_task_queue(c: &mut Criterion) {

    let queue = TaskQueue::new();
    let tasks = generate_tasks();

    c.bench_function("TaskQueue push+fetch", |b| {
        b.iter(|| {

            for task in &tasks {
                queue.push_task(task.clone());
            }

            for _ in 0..tasks.len() {
                queue.fetch_task();
            }

        })
    });
}

// ---------- 2. Channel ----------
fn benchmark_channel(c: &mut Criterion) {

    let (tx, rx) = mpsc::channel();
    let tasks = generate_tasks();

    c.bench_function("Channel push+fetch", |b| {
        b.iter(|| {

            for task in &tasks {
                tx.send(task.clone()).unwrap();
            }

            for _ in 0..tasks.len() {
                rx.recv().unwrap();
            }

        })
    });
}

// ---------- 3. RwLock ----------
fn benchmark_rwlock(c: &mut Criterion) {

    let queue = Arc::new(RwLock::new(VecDeque::new()));
    let tasks = generate_tasks();

    c.bench_function("RwLock push+fetch", |b| {
        b.iter(|| {

            {
                let mut q = queue.write().unwrap();
                for task in &tasks {
                    q.push_back(task.clone());
                }
            }

            {
                let mut q = queue.write().unwrap();
                for _ in 0..tasks.len() {
                    q.pop_front();
                }
            }

        })
    });
}

criterion_group!(
    benches,
    benchmark_task_queue,
    benchmark_channel,
    benchmark_rwlock
);

criterion_main!(benches);
