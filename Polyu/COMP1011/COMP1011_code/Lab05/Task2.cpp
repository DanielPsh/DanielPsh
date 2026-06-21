#include <iostream>
using namespace std;

double rectangleArea(double newWidth, double newHeight)
{
    double area;

    area = newWidth * newHeight;
    return area;

    return;
}//function
int main()
{
    double width;
    double height;

    cout << "Please enter rectangle width: ";
    cin >> width;
    cout << "Please enter rectangle height: ";
    cin >> height;

    cout << "Area: " << rectangleArea(width, height) << endl;

    return 0;
}