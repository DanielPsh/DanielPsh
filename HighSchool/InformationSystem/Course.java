

import java.util.Scanner;
public class Course
{
    private String courseName;
    private Faculty faculty = new Faculty();
    private Faculty[] facultyList = new Faculty[61];
    private int facultyNum = 0;
    private Student[] studentList = new Student[61];
    private int studentNum = 0;
    
    public Course()
    {
        this.courseName = "N/A";
    }
    public Course(String courseName)
    {
        this.courseName = courseName;
    }
    public Course(String courseName, Faculty faculty)
    {
        this.courseName = courseName;
        this.faculty = faculty;
    }
    public String getName()
    {
        return this.courseName;
    }
    public void setName(String courseName)
    {
        this.courseName = courseName;
    }
    public Faculty getFaculty()
    {
        return this.faculty;
    }
    public void setFaculty(Faculty faculty)
    {
        this.faculty = faculty;
    }
    public Faculty[] getFacultyList()
    {
        return this.facultyList;
    }
    public int getFacultyNum()
    {
        return this.facultyNum;
    }
    public void addFaculty(Faculty facultyName)
    {
        this.facultyList[facultyNum] = facultyName;
        facultyNum++;
    }
    public Student[] getStudentList()
    {
        return this.studentList;
    }
    public int getStudentNum()
    {
        return this.studentNum;
    }
    public void addStudent(Student studentName)
    {
        this.studentList[studentNum] = studentName;
        studentNum++;
    }
}