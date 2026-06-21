#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
    int row = 4;
    int col = 4;
    int num[4][4] = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
    int sumD1 = 0;
    int sumD2 = 0;

    for(int i = 0; i < row; i++)
    {
        for(int j = 0; j < col; j++)
        {
            cout << setw(4) << num[i][j];
        }
        cout << endl;
    }
    cout << endl;
    for(int i = 0; i < row; i++)
    {
        // {0,0} + {1,1} + {2,2} + {3,3}
        sumD1 = sumD1 + num[i][i];
        // {0,3} + {1,2} + {2,1} + {3,0}
        sumD2 = sumD2 + num[i][row - 1 - i];
    }
    cout << "Result D1: " << sumD1<< endl;
    cout << "Rsult D2: " << sumD2 << endl;
    return 0;
}