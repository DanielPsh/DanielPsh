// Cube a variable using call-by-value.
#include <iostream>
using namespace std;
void cubeByValue(int*); // prototype

int main()
{
    int number = 5;
    cout << "The original value of number is " << number;

    // pass number by value to cubeByValue
    cubeByValue(&number);

    cout << "\nThe new value of number is " << number << endl;

    return 0;
}

 // calculate and return cube of integer argument
void cubeByValue(int *n)
{
    *n = (*n) * (*n) * (*n); // cube local variable n and return result
}