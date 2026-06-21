#include <iostream>

using namespace std;

int main()
{
    const int ARRAY_SIZE = 10;

    int noList[ARRAY_SIZE] = {1,2,3,4,5,6,7,8,9,10};


    int total = 0;

    for(int i = 0; i < ARRAY_SIZE; i++)
    {
        total = total + noList[i];
    }

    cout << "Total of array element values is: " << total << endl;

    return 0;
}