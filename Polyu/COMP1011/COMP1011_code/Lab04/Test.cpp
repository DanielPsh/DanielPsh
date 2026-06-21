#include <iostream>
using namespace std;
int main() {

	int input;
	// To ensure the input is a prime number between 1 and 10
	do {
		cout << "Please enter a prime number that falls between 1 and 10: ";
		cin >> input;
	} while (!(input == 2 || input == 3 || input == 5 || input == 7));
	cout << "The input is " << input << "." << endl;
	return 0;
}
