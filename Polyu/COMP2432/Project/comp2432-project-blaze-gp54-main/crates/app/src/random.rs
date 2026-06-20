use blaze_core::Task;

pub fn task_signature(task: &Task) -> u64 {
    let mut h = 0x6A09E667F3BCC909u64;
    h = h.rotate_left(7) ^ task.id;
    h = h.wrapping_mul(0x9E3779B97F4A7C15);
    h ^= task.position.x.to_bits().rotate_left(11);
    h = h.wrapping_mul(0xC2B2AE3D27D4EB4F);
    h ^= task.position.y.to_bits().rotate_left(17);
    h = h.wrapping_mul(0x165667B19E3779F9);
    h ^= task.zone.bytes().fold(0u64, |acc, b| {
        acc.wrapping_mul(131).wrapping_add(b as u64)
    });
    h = h.wrapping_mul(0xD6E8FEB86659FD93);
    h ^= task.is_emergency as u64;
    h
}
