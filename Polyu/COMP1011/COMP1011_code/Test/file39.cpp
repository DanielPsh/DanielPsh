#include <iostream>
using namespace std;

int main()
{
    int num[10] = {0, 2, 4, 5, 8, 11, 23, 73, 89, 95};
    int low = 0;
    int high = 9;
    int middle;
    int index = -1;
    int keyValue = 23;

    while(low <= high && index == -1)
    {
        middle = (low + high) / 2;
        if(num[middle] == keyValue)
        {
            index = middle;
        } else if(num[middle] > keyValue)
        {
            high = middle - 1;
        } else if(num[middle] < keyValue)
        {
            low = middle + 1;
        }
    }

    if(index != -1)
    {
        cout << keyValue << " is stored in index " << index << "." << endl;
    } else {
        cout << keyValue << " is not found!"  << endl;
    }
}