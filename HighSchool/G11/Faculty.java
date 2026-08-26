public class Faculty extends Person {
	int facultyID;
	int courseNum = 0;
	private Course[] courseList = new Course[61];

	public Faculty() {
		super("no name", 0);
		this.facultyID = 0;
	}

	public Faculty(String name, int age, int facultyID) {
		super(name, age);
		this.facultyID = facultyID;
	}

	public int getFacultyID() {
		return this.facultyID;
	}

	public void setFacultyID(int facultyID) {
		this.facultyID = facultyID;
	}

	public void addCourse(Course newCourse) {
		this.courseList[courseNum] = newCourse;
		this.courseNum++;
	}

	public Course[] getCourseList() {
		return this.courseList;
	}

	public int getCourseNum() {
		return this.courseNum;
	}

	public void setCourseList(Course[] courseList) {
		this.courseList = courseList;
	}
}