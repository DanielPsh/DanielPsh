// Using the & and * operators.
#include <iostream>
using namespace std;

int main() 
{
		int a; // a is an integer
		int* aPtr; // aPtr is a pointer to an integer
		
		a = 7;
		aPtr = &a; // aPtr assigned address of a
		
		cout << "The address of a is " << &a << endl << "The value of aPtr is "
		<< aPtr << endl;
		
		cout << "The value of a is " << a << endl << "The value of *aPtr is "
		<< *aPtr << endl;
		
		cout << "Showing that * and & are inverses of " << "each other.\n&*aPtr = "
		<< &*aPtr << endl << "*&aPtr = " << *&aPtr << endl;
		
		return 0;
}

