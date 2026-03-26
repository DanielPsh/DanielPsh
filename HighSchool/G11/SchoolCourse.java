

// import Scanner
import java.util.*;

public class SchoolCourse
{
	private static Student[] studentList = new Student[Integer.MAX_VALUE];
	static int studentNum = 0;

	static Faculty[] facultyList = new Faculty[Integer.MAX_VALUE];
	static int facultyNum = 0;

	static Course[] courseList = new Course[Integer.MAX_VALUE];
	static int courseNum = 0;

	public static void main(String[] args)
	{
		// Scanners
		Scanner inputChoiceMain = new Scanner(System.in);
		int choiceMain;
		Scanner inputChoiceStudent = new Scanner(System.in);
		int choiceStudent;
		Scanner inputChoiceCourse = new Scanner(System.in);
		int choiceCourse;
		Scanner inputChoiceFaculty = new Scanner(System.in);
		int choiceFaculty;

		// Greeting
		System.out.println("\n ========================================== \n");
		System.out.println("\n  Welcome to School Management");
		System.out.println("  by DanielPark System v2.0\n");
		System.out.println("\n ========================================== \n");
		
		// Try again loop (do - while loop)
		do
		{
			// Main Menu
			System.out.print("\n[1] STUDENTS\n[2] COURSES\n[3] FACULTY\n[4] Exit\n\nChoose an operation : ");
			choiceMain = inputChoiceMain.nextInt();

			if (choiceMain == 1)
			{
				do
				{
					// Student Menu
					System.out.print("\n ===== STUDENTS =====\n");
					System.out.print("\n[1] Display \n[2] Add \n[3] Edit Name\n[4] Delete Student\n[5] Add Courses to Student\n[6] Sort Name\n[7] Binary Search\n[8] Exit sub menu\n\nChoose an operation: ");
					choiceStudent = inputChoiceStudent.nextInt();

					if(choiceStudent == 1)
					{
						studentDisplay(studentList);
					}
					else if(choiceStudent == 2)
					{
						studentAdd(studentList);
					}
					else if(choiceStudent == 3)
					{
						edit(studentList);
					}
					else if (choiceStudent == 4)
					{
						delete(studentList);
					}
					else if(choiceStudent == 5)
					{
						addCourseToStudent(studentList,courseList);
					}
					else if(choiceStudent == 6)
					{
						selectionSortS( 0, studentList, studentNum - 1);
					}
					else if(choiceStudent == 7)
					{
						studentBinarySearch(studentList);
					}
					else if(choiceStudent == 8)
					{
						System.out.println("Exit sub menu....");
					}
					else
					{
						System.out.println("Not valid!");
					}

				}while(choiceStudent != 8);
			}

			else if(choiceMain == 2)
			{
				do
				{
					// Course Menu
					System.out.print("\n ===== COURSE =====\n");
					System.out.print("\n[1] Display \n[2] Add \n[3] Edit\n[4] Delete\n[5] Add Students to Course\n[6] Sort Name\n[7] Binary Search\n[8] Exit sub menu\n\nChoose an operation: ");
					choiceCourse = inputChoiceCourse.nextInt();
					if(choiceCourse == 1)
					{
						courseDisplay(courseList);
					}
					else if(choiceCourse == 2)
					{
						courseAdd(courseList);
					}
					else if(choiceCourse == 3)
					{
						editC(courseList);
					}
					else if(choiceCourse == 4)
					{
						deleteC(courseList);
					}
					else if(choiceCourse == 5)
					{
						AddStudentsToCourse(courseList,studentList);
					}
					else if(choiceCourse == 6)
					{
						selectionSortC(0, courseList, courseNum-1);
					}
					else if(choiceCourse == 7)
					{
						courseBinarySearch(courseList);
					}
					else if(choiceCourse == 8)
					{
						System.out.println("Exit sub menu....");
					}
					else
					{
						System.out.println("Not valid!");
					}

				}while(choiceCourse != 8);
			}
			else if(choiceMain == 3)
			{
				do
				{
					//Faculty Menu
					System.out.print("\n ===== FACULTY =====\n");
					System.out.print("\n[1] Display \n[2] Add \n[3] Edit\n[4] Delete\n[5] Add Courses to Student\n[6] Sort Name\n[7] Binary Search\n[8] Exit sub menu\n\nChoose an operation: ");
					choiceFaculty = inputChoiceFaculty.nextInt();

					if(choiceFaculty == 1)
					{
						facultyDisplay(facultyList);
					}
					else if(choiceFaculty == 2)
					{
						facultyAdd(facultyList);
					}
					else if(choiceFaculty == 3)
					{
						editF(facultyList);
					}
					else if (choiceFaculty == 4)
					{
						deleteF(facultyList);
					}
					else if(choiceFaculty == 5)
					{
						AddCourseToFaculty(facultyList,courseList);
					}
					else if (choiceFaculty == 6)
					{
						selectionSortF(0, facultyList, facultyNum-1);
					}
					else if (choiceFaculty == 7)
					{
						facultyBinarySearch(facultyList);
					}
					else if (choiceFaculty == 8)
					{
						System.out.println("Exit sub menu....");
					}
					else
					{
						System.out.println("Not valid!");
					}
				}while(choiceFaculty != 8);

			}

			else if(choiceMain == 4)
			{
				choiceMain = 4;
			}

			System.out.println("\n ========================================== \n");
			System.out.println("\n  Welcome to School Management");
			System.out.println("  by DanielPark System v2.0\n");
			System.out.println("\n ========================================== \n");

		}while(choiceMain != 4);

	}

	// students
	public static void studentDisplay(Student[] studentList)
	{
		//checking
		if(studentNum == 0)
		{
			System.out.println("No student yet.\nConsider [2]Add to add Student First.");
		}
		else
		{
			System.out.print("\n ===== STUDENTS =====\n");
			for(int i = 0; i < studentNum; i++)
			{
				System.out.println("\nStudent " + (i + 1) + ": ");
				System.out.println("Name: " + studentList[i].getName() + "\nAge: " + studentList[i].getAge() + "\nID: " + studentList[i].getID());
				System.out.println("");

				if(studentList[i].getCourseNum() == 0)
					System.out.println("No course is taken yet.");
				else
				{
					System.out.println("Student's course list: ");
					for(int j = 0; j < studentList[i].getCourseNum(); j++)
						System.out.println("Student course(s): " + studentList[i].getCourseList()[j].getName());
				}

			}
		}
		return;
	}
	
	public static Student[] studentAdd(Student[] studentList)
	{
		//checking
		Scanner input = new Scanner(System.in);

		System.out.print("Enter student's name: ");
		String name = input.next();
		if(locateStudent(name) != - 1)
			System.out.println("This student is aleady there.");
		else
		{
			System.out.print("Enter student's age: ");
			int age = input.nextInt();
			System.out.print("Enter student's 'ID: ");
			int studentID = input.nextInt();

			Student newStudent = new Student(name, age, studentID);

			studentList[studentNum] = newStudent;
			studentNum++;
		}
		return studentList;
	}

	public static void edit(Student[] studentList)
	{
		Scanner input = new Scanner(System.in);
		Scanner nameC = new Scanner(System.in);

		if (studentNum != 0)
		{
			for(int i = 0; i < studentNum; i++)
				System.out.println("[" + i + "]" + studentList[i].getName());

			System.out.print("Enter the number that you want to edit their name: ");
			int change = input.nextInt();

			System.out.print("Enter the name that you want to edit: ");
			String name = nameC.nextLine();

			System.out.print("Enter age: ");
			int age = nameC.nextInt();

			System.out.print("Enter ID: ");
			int iD = nameC.nextInt();

			studentList[change].setName(name);
			studentList[change].setAge(age);
			studentList[change].setID(iD);
		}
		else
			System.out.println("No student yet");

	}

	public static Student[] delete(Student[] studentList)
	{
		if(studentNum == 0)
		{
			System.out.println("No student yet\nConsider [2]Add to add Student First.");
		}
		else
		{
			Scanner input = new Scanner(System.in);
			System.out.println("\n ===== STUDENTS List ===== \nEnter the number correspond to the student you want to delete from the list: ");
			for(int i = 0; i < studentNum; i++)
				System.out.println("[" + i + "] " + studentList[i].getName());

			int name = input.nextInt();


			if (studentList == null)
				System.out.println("No student yet.");

			for (int i = name; i < studentNum; i++)
			{
				studentList[i] = studentList[i + 1];
			}
			studentNum--;
		}
		return studentList;
	}

	public static void addCourseToStudent(Student[] studentList, Course[] courseList)
	{
		Scanner input = new Scanner(System.in);
		int choice;
		int choice2;

		if (studentNum == 0)
			System.out.print("No student");
		else
		{
			System.out.print("\n ===== STUDENTS List =====\n");
			System.out.print("Choose the student that you want to add a course to: ");
			for(int i = 0; i < studentNum; i++)
				System.out.println("[" + i + "]" + studentList[i].getName());
			System.out.print("Enter your choice: ");

			choice = input.nextInt();
			studentList[choice] = studentList[choice];

			if(courseNum == 0)
			{
				System.out.println("No course yet");
			}
			else
			{
				System.out.print("\n ===== COURSE List =====\n");
				for(int k = 0; k < courseNum; k++)
					System.out.println("[" + k + "]" + courseList[k].getName());
				System.out.print("Enter your choice: ");

				choice2 = input.nextInt();
				courseList[choice2] = courseList[choice2];

				studentList[choice].addCourse(courseList[choice2]);
				System.out.println("Added");
				courseList[choice2].addStudent(studentList[choice]);
				System.out.println("Check it by entering [1] Display!");
			}
		}
	}

	public static void studentBinarySearch(Student[] studentList)
	{
		selectionSortS(0, studentList, studentNum - 1);
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the name that you want to find: ");
		String key = input.nextLine();

		int low = 0;
		int high = studentNum - 1;
		int index = studentBinarySearch(studentList, key, low, high);

		if(index >= 0)
		{
			System.out.println("Found!");
			System.out.println("\nStudent Informations: ");
			System.out.println("Name: " + studentList[index].getName() + "\nAge: " + studentList[index].getAge() + "\nID: " + studentList[index].getID() + "\nCourse: " + studentList[index].getCourseNum());
		}
		else
			System.out.println("Not found!");
	}

	public static int studentBinarySearch(Student[] studentList, String key, int low, int high)
	{
		if (low > high)
		{
			return - low - 1;
		}
		int middle = low + (high - low) / 2;

		if (key.compareTo(studentList[middle].getName()) < 0)
		{
			return studentBinarySearch(studentList, key, 0, middle-1);
		}
		else if (key.equalsIgnoreCase(studentList[middle].getName()))
		{
			return middle;
		}
		else
		{
			return studentBinarySearch(studentList, key, middle + 1, high);
		}
	}

	// courses
	public static void courseDisplay(Course[] courseList)
	{
		if(courseNum == 0)
		{
			System.out.println("No courses yet.\nConsider [2]Add to add Course First.");
		}
		else
		{
			System.out.print("\n ===== COURSE =====\n");
			for(int i = 0; i < courseNum; i++)
			{
				System.out.println("Course " + (i + 1) + ": ");
				System.out.println("Course: " + courseList[i].getName());

				if(courseList[i].getFacultyNum() == 0)
					System.out.println("No faculty yet!\n");
				else
				{
					{
						System.out.println("Faculty List:");
						for (int k = 0; k < courseList[i].getFacultyNum(); k++)
							System.out.print(courseList[i].getFacultyList()[k].getName()+ "\n");
						System.out.print("\n");
					}
				}
			}
		}
	}

	public static Course[] courseAdd(Course[] courseList)
	{
		Scanner input = new Scanner(System.in);
		char choice;

		Scanner input1 = new Scanner(System.in);
		int choice2;

		Course newCourse = new Course();
		Scanner inputName = new Scanner(System.in);
		System.out.print("Enter the name of the course: ");
		String name = inputName.nextLine();

		do
		{
			System.out.print("Add faculty? [y/n]");
			choice = input.next().charAt(0);

			if(choice == 'y')
			{
				System.out.println("Choose a faculty: ");
				if(facultyNum == 0)
					System.out.println("No faculty yet.");
				else
				{
					for(int i = 0; i < facultyNum; i++)
						System.out.println("[" + i +"]" + facultyList[i].getName());
					choice2 = input1.nextInt();

					newCourse.addFaculty(facultyList[choice2]);
					facultyList[choice2].addCourse(newCourse);
				}
			}
			else if(choice == 'n')
			{
				
			}
			else
			{
				System.out.println("Doesn't want to add Faculty");
			}
		}while(choice == 'n' && choice =='N');
		newCourse.setName(name);
		courseList[courseNum] = newCourse;
		courseNum++;
		return courseList;
	}

	public static void editC(Course[] courseList)
	{
		Scanner input = new Scanner(System.in);
		Scanner nameC = new Scanner(System.in);

		if (courseNum != 0)
		{
			for(int i = 0; i < courseNum; i++)
				System.out.println("[" + i + "]" + courseList[i].getName());

			System.out.print("Enter the number that you want to edit: ");
			int change = input.nextInt();

			System.out.print("Enter the course that you want to edit: ");
			String name = nameC.nextLine();

			courseList[change].setName(name);
			System.out.print(courseList[change].getName());
		}
		else
			System.out.print("No course yet\nConsider [2]Add to add Course First.");
	}

	public static Course[] deleteC(Course[] courseList)
	{
		if(courseNum == 0)
		{
			System.out.println("No course yet\nConsider [2]Add to add Course First.");
		}
		else
		{
			Scanner input = new Scanner(System.in);
			System.out.println("\n ===== COURSE List ===== \nEnter the number correspond to the course you want to delete from the list: ");
			for(int i = 0; i < courseNum; i++)
				System.out.println("[" + i + "] " + courseList[i].getName());

			int name = input.nextInt();


			if (courseList == null)
				System.out.println("No course yet.");

			for (int i = name; i < courseNum; i++)
			{
				courseList[i] = courseList[i + 1];
			}
			courseNum--;
		}
		return courseList;
	}

	// Facultys
	public static void facultyDisplay(Faculty[] facultyList)
	{
		if(facultyNum == 0)
			System.out.println("No faculties yet.\nConsider [2]Add to add Faculty First.");
		else
		{
			for(int i = 0; i < facultyNum; i++)
			{
				System.out.println("\nFaculty " + (i + 1) + ": ");
				System.out.println("Name: " + facultyList[i].getName() + "\nAge: " + facultyList[i].getAge() + "\nID: " + facultyList[i].getFacultyID());

				if(facultyList[i].getCourseNum() == 0)
				{
					System.out.println("No course yet");
				}
				else
				{
					System.out.println("Faculty's course list: ");
					for(int j = 0; j < facultyList[i].getCourseNum(); j++)
						System.out.println("This faculty teaches: " + facultyList[i].getCourseList()[j].getName());
				}

			}
		}
		return;
	}

	public static Faculty[] facultyAdd(Faculty[] facultyList)
	{
		Scanner input = new Scanner(System.in);

		System.out.print("Enter faculty's name: ");
		String name = input.next();
		if(locateFaculty(name) != - 1)
			System.out.println("This faculty is aleady there.");
		else
		{
			System.out.print("Enter faculty's age: ");
			int age = input.nextInt();
			System.out.print("Enter faculty's 'ID: ");
			int facultyID = input.nextInt();

			Faculty newFaculty = new Faculty(name, age, facultyID);

			facultyList[facultyNum] = newFaculty;
			facultyNum++;

		}
		return facultyList;
	}

	public static void editF(Faculty[] facultyList)
	{
		Scanner input = new Scanner(System.in);
		Scanner nameC = new Scanner(System.in);

		if(facultyNum == 0)
		{
			System.out.println("No faculty yet!\nConsider [2]Add to add Faculty First.");
		}
		else
		{
			for(int i = 0; i < facultyNum; i++)
				System.out.println("[" + i + "]" + facultyList[i].getName());

			System.out.print("Enter the number that you want to edit: ");
			int change = input.nextInt();

			System.out.print("Enter new faculty: ");
			String name = nameC.nextLine();

			System.out.print("Enter age: ");
			int age = nameC.nextInt();

			System.out.print("Enter ID: ");
			int iD = nameC.nextInt();

			facultyList[change].setName(name);
			facultyList[change].setAge(age);
			facultyList[change].setFacultyID(iD);
		}
	}

	public static Faculty[] deleteF(Faculty[] facultyList)
	{
		if(facultyNum == 0)
		{
			System.out.println("No faculty yet\nConsider [2]Add to add Faculty First.");
		}
		else
		{
			Scanner input = new Scanner(System.in);
			System.out.println("\n ===== FACULTY List ===== \nEnter the number correspond to the faculty you want to delete from the list: ");
			for(int i = 0; i < facultyNum; i++)
				System.out.println("[" + i + "] " + facultyList[i].getName());

			int name = input.nextInt();


			if (facultyList == null)
				System.out.println("No faculty yet.");

			for (int i = name; i < facultyNum; i++)
			{
				facultyList[i] = facultyList[i + 1];
			}
			facultyNum--;
		}
		return facultyList;
	}

	////////////////////////////////////////////////////
	public static void AddStudentsToCourse(Course[] courseList, Student[] studentList)
	{
		Scanner input = new Scanner(System.in);
		int cChange = 0;
		int sChange = 0;

		if(courseNum == 0)
			System.out.println("No course yet.");
		else
		{
			System.out.print("\n ===== COURSE List =====\n");
			for(int j = 0; j < courseNum; j++)
				System.out.println("[" + j + "]" + courseList[j].getName());
			System.out.print("Enter your choice: ");

			cChange = input.nextInt();


			if(studentNum == 0)
				System.out.println("No student yet.");
			else
			{
				System.out.print("\n ===== STUDENT List =====\n");
				for(int i = 0; i < studentNum; i++)
					System.out.println("[" + i + "]" + studentList[i].getName());
				System.out.print("Enter your choice: ");

				sChange = input.nextInt();

				for(int k =0; k > 3; k++)
					System.out.println(courseList[k].getName() + " student" + studentList[k].getName());
				courseList[cChange].addStudent(studentList[sChange]);
				System.out.println("Added.");
				studentList[sChange].addCourse(courseList[cChange]);
				System.out.println("Check it by entering [1] STUDENT [1] Display!");
			}
			return;
		}
	}

	public static void selectionSortS(int low, Student[] studentList, int high)
	{
		if(low < high)
		{
			int indexOfMin = low;
			Student studentMin = studentList[low];
			String stringMin = studentList[low].getName();

			for(int i = low + 1; i <= high; i++)
			{
				if(studentList[i].getName().compareTo(stringMin) < 0)
				{
					stringMin = studentList[i].getName();
					studentMin =  studentList[i];
					indexOfMin = i;
				}
			}
			studentList[indexOfMin] = studentList[low];
			studentList[low] = studentMin;
			selectionSortS(low + 1, studentList, high);
		}
		else
			System.out.println("Student list is sorted in alphabetical order.");
		return;
	}

	public static void selectionSortC(int low, Course[] courseList, int high)
	{
		if(low < high)
		{
			int indexOfMin = low;
			Course courseMin = courseList[low];
			String stringMin = courseList[low].getName();

			for(int i = low + 1; i <= high; i++)
			{
				if(courseList[i].getName().compareTo(stringMin) < 0)
				{
					stringMin = courseList[i].getName();
					courseMin =  courseList[i];
					indexOfMin = i;
				}
			}
			courseList[indexOfMin] = courseList[low];
			courseList[low] = courseMin;
			selectionSortC(low + 1, courseList, high);
		}
		else
			System.out.println("Course list is sorted in alphabetical order.");
		return;
	}

	public static void selectionSortF(int low, Faculty[] facultyList, int high)
	{
		if(low < high)
		{
			int indexOfMin = low;
			Faculty facultyMin = facultyList[low];
			String stringMin = facultyList[low].getName();

			for(int i = low + 1; i <= high; i++)
			{
				if(facultyList[i].getName().compareTo(stringMin) < 0)
				{
					stringMin = facultyList[i].getName();
					facultyMin =  facultyList[i];
					indexOfMin = i;
				}
			}
			facultyList[indexOfMin] = facultyList[low];
			facultyList[low] = facultyMin;
			selectionSortF(low + 1, facultyList, high);
		}
		else
			System.out.println("Faculty list is sorted in alphabetical order.");
		return;
	}

	public static void AddCourseToFaculty(Faculty[] facultyList, Course[] courseList)
	{
		Scanner input = new Scanner(System.in);
		int fChange = 0;
		int cChange = 0;

		if(facultyList.length == 0)
			System.out.println("No faculty yet.");
		else
		{
			System.out.print("\n ===== FACULTY List =====\n");
			for(int j = 0; j < facultyNum; j++)
				System.out.println("[" + j + "]" + facultyList[j].getName());
			System.out.print("Enter your choice: ");

			fChange = input.nextInt();

			if(courseNum == 0)
				System.out.println("No Course yet.");

			else
			{
				System.out.print("\n ===== COURSE List =====\n");
				for(int k = 0; k < courseNum; k++)
					System.out.println("[" + k + "]" + courseList[k].getName());
				System.out.print("Enter your choice: ");

				cChange = input.nextInt();

				facultyList[fChange].addCourse(courseList[cChange]);
				System.out.println("Added");
				courseList[cChange].addFaculty(facultyList[fChange]);
				System.out.println("Check it by Entering [1] Display!");
			}
		}
		return;
	}

	public static void facultyBinarySearch(Faculty[] facultyList)
	{
		selectionSortF( 0, facultyList, facultyNum - 1);
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the name that you want to find: ");
		String key = input.nextLine();

		int low = 0;
		int high = facultyNum - 1;
		int index = facultyBinarySearch(facultyList, key, low, high);

		if(index >= 0)
		{
			System.out.println("Found!");
			System.out.println("\nFaculty Informations: ");
			System.out.println("Name: " + facultyList[index].getName() + "\nAge: " + facultyList[index].getAge() + "\nID: " + facultyList[index].getFacultyID() + "\nCourse: " + facultyList[index].getCourseNum());
		}
		else
			System.out.println("Not found!");
	}

	public static int facultyBinarySearch(Faculty[] facultyList, String key, int low, int high)
	{
		if (low > high)
		{
			return - low - 1;
		}
		int middle = low + (high - low) / 2;

		if (key.compareTo(facultyList[middle].getName()) < 0)
		{
			return facultyBinarySearch(facultyList, key, 0, middle - 1);
		}
		else if (key.equalsIgnoreCase(facultyList[middle].getName()))
		{
			return middle;
		}
		else
		{
			return facultyBinarySearch(facultyList, key, middle + 1, high);
		}
	}

	public static void courseBinarySearch(Course[] courseList)
	{
		selectionSortC( 0, courseList, courseNum - 1);
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the course that you want to find: ");
		String key = input.nextLine();

		int low = 0;
		int high = courseNum - 1;
		int index = courseBinarySearch(courseList, key, low, high);

		if(index >= 0)
		{
			System.out.println("Found!");
			System.out.println("\nCourse Informations: ");
			System.out.println("Name of the Course: " + courseList[index].getName() + "\nFaculty: " + courseList[index].getFacultyNum());
		}
		else
			System.out.println("Not found!");
	}

	public static int courseBinarySearch(Course[] courseList, String key, int low, int high)
	{
		if (low > high)
		{
			return - low - 1;
		}
		int middle = low + (high - low) / 2;

		if (key.compareTo(courseList[middle].getName()) < 0)
		{
			return courseBinarySearch(courseList, key, 0, middle-1);
		}
		else if (key.equalsIgnoreCase(courseList[middle].getName()))
		{
			return middle;
		}
		else
		{
			return courseBinarySearch(courseList, key, middle + 1, high);
		}
	}

	public static int locateStudent(String name)
	{
		int index = 0;

		if(studentNum == 0)
			index = -1;
		else
		{
			for(int i = 0; i < studentNum; i++)
			{
				if(studentList[i].getName() == name)
				{
					index = i;
					break;
				}
				else
					index = -1;
			}
		}
		return index;
	}

	public static int locateFaculty(String name)
	{
		int index = 0;

		if(facultyNum == 0)
			index = -1;
		else
		{
			for(int i = 0; i < facultyNum; i++)
			{
				if(facultyList[i].getName() == name)
				{
					index = i;
					break;
				}
				else
					index = -1;
			}
		}
		return index;
	}
}