#include <iostream>
using namespace std;

int power(int x, int y)
{
    int result = 1;
    for(int i = 0; i < y; i++)
        result = result * x;
    return result;
}
int power_recur(int x, int y)
{
    if(y == 1)
        return x;
    return x * power_recur(x, y - 1);
}
int main()
{
    int base, exp;
    cout << "Enter Base: ";
    cin >> base;
    cout << "Enter Exp: ";
    cin >> exp;
    cout << "Power: " << power_recur(base, exp) << endl;

    return 0;
}