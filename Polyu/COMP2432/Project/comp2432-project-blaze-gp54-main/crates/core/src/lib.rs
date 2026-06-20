// Role A
/*
Create all shared data structures in crates/core/src/types.rs
Position, Task (id, position, zone, description)
RobotId, Robot (id, position, status)
RobotStatus (Online/Offline)

Add useful helper functions if needed (e.g. distance calculation for nearest task)
Make sure everything is pub and properly exported
*/
pub mod types;

pub use types::{Heartbeat, Position, Robot, RobotId, RobotStatus, Task};
