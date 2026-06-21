#include <iostream>
using namespace std;

int main()
{
    char sentence1[15];
    cout << "Enter the string: ";
    //cin >> sentence1;
    cin.getline(sentence1, 15, '\n');
    cout << sentence1 << endl;


    cin >> sentence1;
    cout << sentence1 << endl;
}