#include <iostream>
#include <cmath>
using namespace std;

int main()
{
    int choice;
    double length, width, height, radius;
    const double pi = 3.14;
    cout << "Student ID: 24101315d" << endl;
    cout << "Student Name: PARK SANGHYUN" << endl;

    cout << "===== Menu =====" << endl;
    cout << "[1] Area of a rectangle (Area = length x width)\n[2] Circumference of a circle (Circumference = 2 x π x r)\n[3] Volume of a rectangular box (Volume = length x width x height)\n[4] Diagonal of a rectangle (Diagonal = √(length² + width²))\nEnter your choice: ";
    cin >> choice;

    if(choice == 1)
    {
        double area;
        cout << "Enter length: ";
        cin >> length;
        cout << "Enter width: ";
        cin >> width;

        area = length * width;

        cout << "Area = " << area << endl;

    }else if(choice == 2)
    {
        double circumference;

        cout << "Enter radius: ";
        cin >> radius;

        circumference = 2 * pi * radius;

        cout << "Circumference = " << circumference << endl;

    }else if(choice == 3)
    {
        double volume;
        cout << "Enter length: ";
        cin >> length;
        cout << "Enter width: ";
        cin >> width;
        cout << "Enter height: ";
        cin >> height;

        volume = length * width * height;

        cout << "Volume = " << volume << endl;

    }else if(choice == 4)
    {
        double diagonal;
        cout << "Enter length: ";
        cin >> length;
        cout << "Enter width: ";
        cin >> width;

        if(length > 0 || width > 0)
        {
            diagonal = sqrt(pow(length, 2) + pow(width, 2));
            cout << "Diagonal = " << diagonal << endl;
        }else{
            cout << "Invalid Input" << endl;
        }
    }else{
        cout << "Invalid Input" << endl;
    }
    return 0;
}