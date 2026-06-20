use std::env;
use std::time::SystemTime;
use std::time::UNIX_EPOCH;

#[derive(Debug, Clone, Copy)]
pub enum SeedMode {
    Time,
    Fixed(u64),
}

#[derive(Debug, Clone, Copy)]
pub struct Config {
    pub robots: usize,
    pub duration_secs: u64,
    pub heartbeat_ms: u64,
    pub task_gen_ms: u64,
    pub timeout_ms: u64,
    pub lookahead: usize,
    pub seed_mode: SeedMode,
}

impl Default for Config {
    fn default() -> Self {
        Self {
            robots: 5,
            duration_secs: 20,
            heartbeat_ms: 700,
            task_gen_ms: 300,
            timeout_ms: 2500,
            lookahead: 3,
            seed_mode: SeedMode::Time,
        }
    }
}

pub fn current_time_seed() -> u64 {
    match SystemTime::now().duration_since(UNIX_EPOCH) {
        Ok(d) => d.as_nanos() as u64,
        Err(_) => 0,
    }
}

pub fn resolve_seed(mode: SeedMode) -> u64 {
    match mode {
        SeedMode::Time => current_time_seed(),
        SeedMode::Fixed(v) => v,
    }
}

pub fn parse_config() -> Config {
    let mut cfg = Config::default();
    for arg in env::args().skip(1) {
        if let Some(v) = arg.strip_prefix("--robots=") {
            if let Ok(n) = v.parse::<usize>() {
                cfg.robots = n.max(1);
            }
        } else if let Some(v) = arg.strip_prefix("--duration=") {
            if let Ok(n) = v.parse::<u64>() {
                cfg.duration_secs = n.max(1);
            }
        } else if let Some(v) = arg.strip_prefix("--heartbeat-ms=") {
            if let Ok(n) = v.parse::<u64>() {
                cfg.heartbeat_ms = n.max(100);
            }
        } else if let Some(v) = arg.strip_prefix("--task-gen-ms=") {
            if let Ok(n) = v.parse::<u64>() {
                cfg.task_gen_ms = n.max(100);
            }
        } else if let Some(v) = arg.strip_prefix("--timeout-ms=") {
            if let Ok(n) = v.parse::<u64>() {
                cfg.timeout_ms = n.max(300);
            }
        } else if let Some(v) = arg.strip_prefix("--lookahead=") {
            if let Ok(n) = v.parse::<usize>() {
                cfg.lookahead = n.max(1);
            }
        } else if let Some(v) = arg.strip_prefix("--seed=") {
            let value = v.trim();
            if value.eq_ignore_ascii_case("t") || value.eq_ignore_ascii_case("time") {
                cfg.seed_mode = SeedMode::Time;
            } else if let Ok(n) = value.parse::<u64>() {
                cfg.seed_mode = SeedMode::Fixed(n);
            } else {
                println!(
                    "[Config] invalid --seed={value}; use --seed=t|time or --seed=<integer>. Fallback to time seed."
                );
                cfg.seed_mode = SeedMode::Time;
            }
        }
    }

    let min_timeout = cfg.heartbeat_ms + 100;
    if cfg.timeout_ms < min_timeout {
        println!(
            "[Config] timeout-ms={} is too small; adjusted to {} (heartbeat + 100ms)",
            cfg.timeout_ms, min_timeout
        );
        cfg.timeout_ms = min_timeout;
    }

    cfg
}
