#include <iostream>
using namespace std;

void isEven(int newInteger)
{
    if(newInteger % 2 == 0)
    {
        cout << newInteger << " is an even integer." << endl;
    }else{
        cout << newInteger << " is an odd integer." << endl;
    }
}
int main()
{
    int Integer;
    cout << "Enter an integer: ";
    cin >> Integer;

    //cout << isEven(Integer) << endl;

    isEven(Integer);
    return 0;
}