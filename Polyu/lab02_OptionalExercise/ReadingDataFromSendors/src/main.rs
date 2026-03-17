use std::sync::atomic::{AtomicBool, Ordering};
use std::sync::{Arc, mpsc};
use std::thread::JoinHandle;
use std::time::Duration;

use crossterm::event::{self, Event, KeyCode};
use rand::random;

fn main() {
    // Create thermometers
    let (mut t1, rx1) = Thermometer::new(1, 1000);
    let (mut t2, rx2) = Thermometer::new(2, 1500);
    let (mut t3, rx3) = Thermometer::new(3, 2000);

    let receivers = vec![rx1, rx2, rx3];
    let mut thermometers = vec![t1, t2, t3];

    println!("Press 1, 2, 3 to toggle thermometers. Press 'q' to quit.");

    loop {
        // Handle keyboard input (non-blocking)
        if event::poll(Duration::from_millis(100)).unwrap() {
            if let Event::Key(key_event) = event::read().unwrap() {
                match key_event.code {
                    KeyCode::Char('1') => toggle(&mut thermometers[0]),
                    KeyCode::Char('2') => toggle(&mut thermometers[1]),
                    KeyCode::Char('3') => toggle(&mut thermometers[2]),
                    KeyCode::Char('q') => {
                        println!("Exiting...");
                        break;
                    }
                    _ => {}
                }
            }
        }

        // Read measurements
        for rx in &receivers {
            while let Ok(measurement) = rx.try_recv() {
                println!(
                    "[Thermometer {}] Temp: {:.2} °C at {:?}",
                    measurement.thermometer_id,
                    measurement.temperature,
                    measurement.timestamp
                );
            }
        }
    }
}

fn toggle(t: &mut Thermometer) {
    if t.measuring.load(Ordering::SeqCst) {
        t.measuring.store(false, Ordering::SeqCst);
    } else {
        t.start_measuring();
    }
}

/* ================= Thermometer ================= */

struct Thermometer {
    id: u8,
    measuerment_interval: u64,
    measuring: Arc<AtomicBool>,
    thread: Option<JoinHandle<()>>,
    producer: mpsc::Sender<MeasureResult>,
}

impl Thermometer {
    pub fn new(id: u8, measuerment_interval: u64)
        -> (Self, mpsc::Receiver<MeasureResult>)
    {
        let (producer, consumer) = mpsc::channel();
        (
            Thermometer {
                id,
                measuerment_interval,
                measuring: Arc::new(AtomicBool::new(false)),
                thread: None,
                producer,
            },
            consumer,
        )
    }

    pub fn start_measuring(&mut self) {
        if self.measuring.load(Ordering::SeqCst) {
            return;
        }

        self.measuring.store(true, Ordering::SeqCst);

        let id = self.id;
        let interval = self.measuerment_interval;
        let measuring_flag = self.measuring.clone();
        let producer = self.producer.clone();

        self.thread = Some(std::thread::spawn(move || {
            while measuring_flag.load(Ordering::SeqCst) {
                producer
                    .send(MeasureResult::new(id, random::<f32>() * 100.0))
                    .unwrap();

                std::thread::sleep(Duration::from_millis(interval));
            }
            println!("Thermometer {} stopped.", id);
        }));
    }
}

/* ================= Measurement ================= */

#[derive(Debug, Clone, Copy)]
struct MeasureResult {
    pub thermometer_id: u8,
    pub temperature: f32,
    pub timestamp: std::time::SystemTime,
}

impl MeasureResult {
    pub fn new(thermometer_id: u8, temperature: f32) -> Self {
        MeasureResult {
            thermometer_id,
            temperature,
            timestamp: std::time::SystemTime::now(),
        }
    }
}

