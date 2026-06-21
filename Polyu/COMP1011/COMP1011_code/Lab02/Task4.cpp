#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    //declare
    double a,b,c;

    //user input
    cout << "Please Enter a: ";
    cin >> a;

    cout << "Please Enter b: ";
    cin >> b;

    cout << "Please Enter c: ";
    cin >> c;

    double quadratic = pow(b,2) - 4 * a * c;

    if(quadratic == 0)
    {
        double root = -b / (2 * a);
        cout << "Repeated root: x = " << root << endl;
    } else if(quadratic > 0){
        double root1 = (-b + sqrt(quadratic)) / (2 * a);
        double root2 = (-b - sqrt(quadratic)) / (2 * a);
        cout << "Two real roots: x = " << root1 << " or " << root2 << endl;
    } else {
        cout << "No real root" << endl;
    }

    return 0;
}