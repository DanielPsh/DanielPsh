#include <iostream>
using namespace std;

int sum(int a, int b)
{
    return a + b;
}

int sum(int a, int b, int c)
{
    return a + b + c;
}

double sum(double a, double b)
{
    return a + b;
}

double sum(int a, double b)
{
    return a + b;
}
int main()
{
    int a = 3;
    int b = 7;
    cout << "int sum(int, int): " << sum(a, b) << endl;

    int c = 5;
    cout << "int sum(int, int, int): " << sum(a, b, c) << endl;

    double x = 2.0;
    double y = 8.0;
    cout << "double sum(double, double): " << sum(x, y) << endl;

    cout << "int sum(int, double): " << sum(a, x) << endl;


    cout << sum(1, 2) << endl;
    cout << sum(1, 2, 3) << endl;
    cout << sum(1.1, 2.5) << endl;
    cout << sum(1, 2.0) << endl;
    //cout << sum(1.5, 2);
    cout << sum((int)1.5, (double)2) << endl; // type cast
    cout << sum(1.5, 2.5, 3.5) << endl;
    x = sum(1, 2); // does the change of x’s data type affects the function call
}