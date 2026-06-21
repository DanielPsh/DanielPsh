#include <iostream>
using namespace std;

int main()
{
    int a = 10; // a is an integer
    int *aPtr = &a; // aPtr is a pointer to integer a
    int &ref = a; // ref is a reference (or alias) for a

    // All the above references have the same value they store or point to
    cout << "&a is: " << &a << " , \t &aPtr: " << &aPtr << endl;
    cout << "ref is: " << ref << " , \t &ref: " << &ref << endl;
    return 0;
}