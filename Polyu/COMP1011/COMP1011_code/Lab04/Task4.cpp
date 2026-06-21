#include <iostream>
using namespace std;

int main()
{
    const int SIZE = 10;
    int numbers[SIZE];
    int input;

    for(int i = 0; i < 10; i++)
    {
        do {
            cout << "Please enter number " << (i + 1) << " [10 - 100]: ";
            cin >> input;
        } while (input < 10 || input > 100);
        numbers[i] = input;
    }
    
    for(int i = 0; i < SIZE; i++)
    {
        bool isDuplicate = false;

        for(int j = 0; j < SIZE; j++)
        {
            if (i != j && numbers[i] == numbers[j]) {
                isDuplicate = true;
                break;
            }
        }
        if(!isDuplicate)
        {
            cout << numbers[i] << " ";
        }
    }
    cout << endl;
    return 0;
    

}