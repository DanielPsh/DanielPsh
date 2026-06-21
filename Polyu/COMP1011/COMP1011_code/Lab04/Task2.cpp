#include <iostream>
using namespace std;

int main()
{
    const int MAX_SIZE = 1000;
    int data[MAX_SIZE];
    int count = 0;
    int input;
    
    cout << "Enter a sequence of integer (-999 to finish): ";

    while(true)
    {
        cin >> input;
        //Ensure the number of elements
        if(input == -999)
        {
            break;
        }
        if(input < MAX_SIZE)
        {
            data[count] = input;
            count++;
        }else{
            cout << "limit exceeded" << endl;
            break;
        }
    }

    int alternatingSum = 0;
    for(int i = 0; i < count;i ++)
    {
        if(i % 2 == 0)
        {
            alternatingSum = alternatingSum + data[i];
        }else{
            alternatingSum = alternatingSum - data[i];
        }
    }

    cout << alternatingSum << endl;

    return 0;
}