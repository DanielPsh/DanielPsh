

public class Student extends Person
{
	int studentID;
	int courseNum = 0;
	private Course[] courseList = new Course[61];
	//private Course[] course = new Course[courseNum];

	public Student()
	{
		super("no name", 0);
		this.studentID = 0;
	}
	public Student(String name, int age, int studentID)
	{
		super(name,age);
		this.studentID = studentID;
	}
	public int getID()
	{
		return this.studentID;
	}
	public void setID(int studentID)
	{
		this.studentID = studentID;
	}
	public void addCourse(Course newCourse)
	{
		this.courseList[courseNum] = newCourse;
		this.courseNum++;
	}
	public Course[] getCourseList()
	{
		return this.courseList;
	}
	public int getCourseNum()
	{
	 	return this.courseNum;
	}
	public void setCourseList(Course[] courseList)
	{
		this.courseList = courseList;
	}
}