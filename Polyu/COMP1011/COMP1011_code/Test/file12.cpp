#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
    int num[3][4] = {{1, 23, 456, 7890}, {12, 345, 6789, 1}, {123, 4567, 89}};
    
    for (int i = 0; i < 3; i++) { // row
        for (int j = 0; j < 4; j++) { // col
            cout << setw(7) << num[i][j] << " ";
        }
        cout << endl;
    }
}