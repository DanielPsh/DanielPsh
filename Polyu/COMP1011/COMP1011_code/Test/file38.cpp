#include <iostream>
using namespace std;

int power(int x, int y)
{
    int result = 1;
    for(int i = 0; i < y; i++)
    {
        result = result * x;
    }
    return result;
}

int power_rec(int x, int y)
{
    if (y == 0) {
        return 1;
    }
    return x * power(x, y - 1);
}

int main()
{
    int x, y;
    cout << "Input x: ";
    cin >> x;
    cout << "Input y: ";
    cin >> y;

    cout << "Result: " << power(x, y) << endl;
}