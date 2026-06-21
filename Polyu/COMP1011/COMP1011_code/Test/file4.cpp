#include <iostream>

using namespace std;

int main()
{
    for (int i = 0, j = 0; j + i <= 10; i++, j++) 
    {
	    cout << 2 * j + i << endl;
    }

    return 0;
}