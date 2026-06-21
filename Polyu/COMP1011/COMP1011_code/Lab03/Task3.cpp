#include <iostream>

using namespace std;

int main()
{
    int integer;
    int sum = 0;
    int count = 0;

    cout << "Enter integers (999 to end): ";

    while (true)
    {
        cin >> integer;
        if(integer == 999)
        {
            break;
        }
        sum = sum + integer;
        count++;
    }

    if(count > 0)
    {
        double avg = static_cast<double>(sum) / count; // Avg calculation
        cout << "The Average is: " << avg << endl;
    } else {
        cout << "No integer were entered." << endl;
    }
    return 0;
}