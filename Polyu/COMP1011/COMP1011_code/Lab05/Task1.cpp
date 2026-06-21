#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
    //declare
    //int num [row][col]
    int num[5][5];


    for(int i = 0; i < 5; i++)//row
    {
        for(int j = 0; j < 5; j++)//col
        {
            num[i][j] = (i + 1) * (j + 1);
        }
        cout << endl;
    }

    for(int i = 0; i < 5; i++)
    {
        for(int j = 0; j < 5; j++)
        {
            cout << setw(5) << num[i][j];
        }
        cout << endl;
    }
    return 0;
}