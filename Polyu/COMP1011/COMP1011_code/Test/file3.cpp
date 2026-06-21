#include <iostream>
using namespace std;
int main()
{
    int integer;
    cout << "Please input an integer: ";
    cin >> integer;
    int store;
    store = integer;
    int result = 1;
	
    while (integer != 0)
    {
		result = result * integer;
        integer = integer - 1;
	}
    cout << "The factorial of " << store << " is " << result << endl;
	return 0;
}

