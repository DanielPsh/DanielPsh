// Class average program with counter-controlled repetition.
#include <iostream>
using namespace std;
int main() {
    double total; // sum of marks input by user
    int counter; // keep track of the number of marks entered
    int mark; // mark value
    double average; // average of marks
    // initialization phase
    total = 0; counter = 1; // initialize total
    // initialize loop counter
    // processing phase
    while (counter <= 10) { // loop 10 times
        cout << "Enter a mark: "; // prompt for input
        cin >> mark; // read mark from user
        total = total + mark; // add mark to total
        counter = counter + 1; // increment counter
    }
    // termination phase
    average = total / 10; // integer division
    // display result
    cout << "Class average is " << average << endl;
    // indicate program ended successfully
    return 0;
}