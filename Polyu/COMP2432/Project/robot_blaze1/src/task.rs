#[derive(Clone)]
pub struct Task {
    pub id: u32,
    pub description: String,
    pub zone: u32,
}

impl Task {

    pub fn new(id: u32, description: &str, zone: u32) -> Self {

        Task {
            id,
            description: description.to_string(),
            zone,
        }

    }

}