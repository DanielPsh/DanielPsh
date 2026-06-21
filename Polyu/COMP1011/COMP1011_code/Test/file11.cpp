#include <iostream>
using namespace std;

int main()
{
    //int row, col;
    //int num[row][col];
    
    int num[2][3] = {{1,6,7},{9,3,4}};
    int sum = 0;

    //cout << "Enter Row: ";
    //cin >> row;
    //cout << "Enter Col: ";
    //cin >> col;

    //for(int i = 0; i < row; i++){
    //    for(int j = 0; j < col; j++){
    //        cout << "Input number: ";
    //        cin >> num[row][col];
    //    }
    //}

    for (int i = 0; i < 2; i++) { // row
        for (int j = 0; j < 3; j++) { // col
            sum = sum + num[i][j];
        }
    }
    cout << "The sum of all elements: " << sum << endl;
    return 0;
}