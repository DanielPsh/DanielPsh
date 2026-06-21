#include <iostream>
using namespace std;

int main()
{
    /*
    int a = 10; int b = 5; // a is an integer
    // b is an integer
    int *aPtr = &a; // aPtr is a pointer to integer a
    aPtr = &b;
    int &ref = a; // ref is a reference (or alias) for a
    return 0;
    */

    int x = 1;
    int *ref = &x;
    cout << sizeof(ref);
    return 0;
}