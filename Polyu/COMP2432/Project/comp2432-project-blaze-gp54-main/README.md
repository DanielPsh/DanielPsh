# Project Blaze: Medical Care Robot Coordination System

COMP2432 Operating Systems Project B (AY 2025-2026)

## Overview
This workspace implements a minimal robot coordination core with OS concurrency focus:
- Thread-safe `TaskQueue` for multi-robot task fetching
- Emergency-aware scheduling (`is_emergency` + nearest-first dispatch)
- `ZoneManager` mutual exclusion for shared zones
- `HealthMonitor` heartbeat timeout detection
- `app` simulation that demonstrates all required behaviors

## Workspace Structure
- `crates/core`: shared types (`Task`, `Position`, `RobotId`, etc.)
- `crates/task-queue`: concurrent task queue implementation
- `crates/zone-control`: zone lock manager
- `crates/health-monitor`: heartbeat and offline checker
- `crates/taskgen`: process-mode task generator (JSON over stdio pipe)
- `crates/app`: executable simulation and integration (spawns `taskgen` child mode)
- `doc/REPORT.md`: written report draft
- `doc/diagram/`: architecture and sequence diagrams

## Build
```bash
cargo build --release
```

## Test
```bash
cargo test
```

## Run Demo
```bash
cargo run -- --robots=5 --duration=20 --heartbeat-ms=700 --task-gen-ms=300 --timeout-ms=2500 --lookahead=3 --seed=t
```

You can still run explicitly by package if preferred:

```bash
cargo run -p comp2432-project-blaze-gp54 -- --robots=5 --duration=20 --heartbeat-ms=700 --task-gen-ms=300 --timeout-ms=2500 --lookahead=3 --seed=t
```

## CLI Parameters
- `--robots=<n>`: number of robot worker threads.
- `--duration=<sec>`: total simulation duration in seconds.
- `--heartbeat-ms=<ms>`: robot heartbeat interval.
- `--task-gen-ms=<ms>`: task generation interval.
- `--timeout-ms=<ms>`: health timeout threshold.
- `--lookahead=<n>`: non-emergency scheduling lookahead window.
- `--seed=t|time|<integer>`: random seed source; `t`/`time` uses current time, integer makes runs reproducible.

Runtime guard rule:
- `timeout-ms` is automatically adjusted to at least `heartbeat-ms + 100` to avoid false offline detection.

Task generation:
- The app starts a process-mode generator using stdio pipe communication.
- Parent (`crates/app`) sends bootstrap config as one JSON line.
- Child mode (`--taskgen-child`) emits one task JSON per line.
- Child mode is internal only; do not run it manually.

Runtime metrics:
- `Zone lock latency`: lock acquisition attempt duration only.
- `Zone wait incl defer`: end-to-end wait including defer/retry periods.
- `Deferred tasks wait (avg)`: average wait for tasks that actually experienced deferral.

## Demonstration Checklist
- Multiple robots concurrently fetch tasks from shared queue.
- Emergency tasks are prioritized by nearest distance to requesting robot.
- Zone mutual exclusion holds: same zone cannot be occupied by two robots simultaneously.
- One robot is intentionally failed mid-run; health monitor marks it offline after timeout.

## Notes
- Design intentionally focuses on correctness and safe synchronization, not advanced scheduling.
- The implementation uses `Mutex`, `Condvar`, `RwLock`, and `AtomicBool` primitives.


### Special
Robot 1 intentionally fails midway to demonstrate timeout/offline behavior.