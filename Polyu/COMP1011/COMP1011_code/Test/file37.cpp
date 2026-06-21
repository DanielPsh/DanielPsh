#include <iostream>
#include <cstring>
using namespace std;

struct Staff
{
    int StaffID;
    char firstname[20];
    char lastname[20];
    char HKID[10];
};

struct Student
{
    int StudentID;
    char firstname[20];
    char lastname[20];
    char HKID[10];
};

char* getFullName(Student *sptr)
{
    char* fullname = strcat(strcat(sptr->firstname, " "), sptr->lastname);
    return fullname;
}

int main()
{
    Student student;
    student.StudentID = 1;
    strcpy(student.firstname, "Harry");
    strcpy(student.lastname, "Potter");

    Staff staff;
    staff.StaffID = 2;

    cout << getFullName(&student);

    Staff *sptr = &staff;
    //cout << (*sptr).StaffID << endl;

    return 0;
}
