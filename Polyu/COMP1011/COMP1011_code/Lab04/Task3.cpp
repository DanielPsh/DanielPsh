#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
    double fraction = 1.0; // Start with 1
    int count = 0; // Count of divisions

    cout << fixed << setprecision(16);

    // Loop until the fraction is less than or equal to 0.0001
    while(fraction > 0.0001)
    {
        fraction = fraction / 2.0;
        cout << fraction << endl;
        count++;
    }

    // Print the last value of fraction (which is now <= 0.0001)
    cout << fraction << " is just less than or equal to 0.0001" << endl;
    cout << "It needs " << count << " divisions." << endl;

    return 0;
}
