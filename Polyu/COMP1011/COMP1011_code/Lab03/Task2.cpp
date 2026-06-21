#include <iostream>

using namespace std;

int main() {

    int rows, columns;
    char theChar;

    cout << "How many rows? ";
    cin >> rows;
    //cout << "How many columns? ";
    //cin >> columns;
    cout << "The character to be printed: ";
    cin >> theChar;

    int i = 1;
    while(i <= rows) {
        int j = 0;
        while(j < i) {
            cout << theChar;
            j++;
        }
        cout << "\n";
        i++;
    }
    return 0;
}

//what is the aim of the program?
