/// 2D coordinate used by robots and tasks in the simulation map.
#[derive(Debug, Clone, Copy, PartialEq)]
pub struct Position {
    /// Horizontal coordinate.
    pub x: f64,
    /// Vertical coordinate.
    pub y: f64,
}

impl Position {
    /// Creates a new position from `(x, y)`.
    pub fn new(x: f64, y: f64) -> Self {
        Self { x, y }
    }

    /// Returns Euclidean distance to another position.
    pub fn distance_to(&self, other: &Self) -> f64 {
        let dx = self.x - other.x;
        let dy = self.y - other.y;
        (dx * dx + dy * dy).sqrt()
    }
}

/// Unique identifier type for a robot.
pub type RobotId = u64;

/// Runtime liveness state of a robot.
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum RobotStatus {
    /// Robot is considered alive and available.
    Online,
    /// Robot is considered unavailable due to timeout/failure.
    Offline,
}

/// Snapshot of a robot's identity, location, and status.
#[derive(Debug, Clone, PartialEq)]
pub struct Robot {
    /// Robot unique id.
    pub id: RobotId,
    /// Current robot position.
    pub position: Position,
    /// Current robot status.
    pub status: RobotStatus,
}

impl Robot {
    /// Creates a robot record.
    pub fn new(id: RobotId, position: Position, status: RobotStatus) -> Self {
        Self {
            id,
            position,
            status,
        }
    }
}

/// Heartbeat message emitted by a robot at a point in time.
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub struct Heartbeat {
    /// Source robot id.
    pub robot_id: RobotId,
    /// Timestamp when the heartbeat was produced.
    pub timestamp: std::time::Instant,
}

impl Heartbeat {
    /// Creates a heartbeat with an explicit timestamp.
    pub fn new(robot_id: RobotId, timestamp: std::time::Instant) -> Self {
        Self {
            robot_id,
            timestamp,
        }
    }

    /// Creates a heartbeat using the current instant.
    pub fn now(robot_id: RobotId) -> Self {
        Self {
            robot_id,
            timestamp: std::time::Instant::now(),
        }
    }
}

/// Work item assigned to robots for execution.
#[derive(Debug, Clone, PartialEq)]
pub struct Task {
    /// Task unique id.
    pub id: u64,
    /// Task location used by nearest-first scheduling.
    pub position: Position,
    /// Zone that must be acquired before task execution.
    pub zone: String,
    /// Human-readable task description.
    pub description: String,
    /// Whether the task is emergency-priority.
    pub is_emergency: bool,
    /// Simulated execution duration in milliseconds.
    pub execution_ms: u64,
}

impl Task {
    /// Creates a task instance.
    pub fn new(
        id: u64,
        position: Position,
        zone: impl Into<String>,
        description: impl Into<String>,
        is_emergency: bool,
        execution_ms: u64,
    ) -> Self {
        Self {
            id,
            position,
            zone: zone.into(),
            description: description.into(),
            is_emergency,
            execution_ms,
        }
    }
}
