#include <iostream>
using namespace std;

int main()
{
    cout << "char\t\t" << sizeof(char) << "\t\t" << sizeof(char) * 8 << "\n";
    cout << "char\t\t" << sizeof(char) << "\t\t" << sizeof(char) * 8 << "\n";
    cout << "short\t\t" << sizeof(short) << "\t\t" << sizeof(short) * 8 << "\n";
    cout << "int\t\t" << sizeof(int) << "\t\t" << sizeof(int) * 8 << "\n";
    cout << "long\t\t" << sizeof(long) << "\t\t" << sizeof(long) * 8 << "\n";
    cout << "long long\t" << sizeof(long long) << "\t\t" << sizeof(long long) * 8 << "\n";
    cout << "float\t\t" << sizeof(float) << "\t\t" << sizeof(float) * 8 << "\n";
    cout << "double\t\t" << sizeof(double) << "\t\t" << sizeof(double) * 8 << "\n";
    cout << "long double\t" << sizeof(long double) << "\t\t" << sizeof(long double) * 8 << "\n";
    cout << "bool\t\t" << sizeof(bool) << "\t\t" << sizeof(bool) * 8 << "\n";
    
    return 0;
}