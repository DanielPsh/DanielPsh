#include <iostream>
#include <cmath>
using namespace std;

struct Point
{
    double x;
    double y;
};

int main()
{
    Point p1,p2,p3;
    double area;

    cout << "Please enter the first coordinate of x: ";
    cin >> p1.x;
    cout << "Please enter the first coordinate of y: ";
    cin >> p1.y;

    cout << "Please enter the second coordinate of x: ";
    cin >> p2.x;
    cout << "Please enter the second coordinate of y: ";
    cin >> p2.y;

    cout << "Please enter the third coordinate of x: ";
    cin >> p3.x;
    cout << "Please enter the third coordinate of y: ";
    cin >> p3.y;

    area = 0.5 * abs(p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y));
    cout << "The area of the Triangle is: " << area << endl;

    return 0;
}