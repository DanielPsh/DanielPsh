#include <iostream>
using namespace std;
int main()
{
    int a, b, c;
    cout << "Enter 3 numbers: ";
    cin >> a;
    cin >> b;
    cin >> c;

    if (c == (a + b))
    {
        cout << "c = a + b \n";
    }

    return 0;
}