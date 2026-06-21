#include <iostream>
using namespace std;
void modify(int &x, double y, char z)
{
    x *= 48 + 10;
    z = tolower(z);
    y = 3.1415;
}

int main() {
    int num = 69;
    double decimal = 3.06247;
    char m = 'M';
    modify(num, decimal, m);
    cout << num << " " << decimal << " " << m << endl;
    return 0;
}