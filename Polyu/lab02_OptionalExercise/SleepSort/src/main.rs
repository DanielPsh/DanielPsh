use std::thread;
use std::time::Duration;

pub fn sleep_sort(numbers: Vec<u64>) {
    let mut handles = Vec::new();

    for n in numbers {
        let handle = thread::spawn(move || {
            // Sleep proportional to value
            thread::sleep(Duration::from_millis(n * 10));
            println!("{}", n);
        });
        handles.push(handle);
    }

    // Wait for all threads to finish
    for h in handles {
        h.join().unwrap();
    }
}

fn main() {
    let nums = vec![9, 1, 4, 7, 3, 2];
    sleep_sort(nums);
}

