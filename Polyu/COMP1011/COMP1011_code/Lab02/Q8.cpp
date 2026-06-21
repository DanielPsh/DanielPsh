#include <iostream>

using namespace std;

int main() {

    double slope, xcoord1, ycoord1, xcoord2, ycoord2;

    cout << "Input x coordinate of the first point: ";
	cin >> xcoord1;
	cout << "Input y coordinate of the first point: ";
	cin >> ycoord1;

	cout << "Input x coordinate of the second point: ";
	cin >> xcoord2;
	cout << "Input y coordinate of the second point: ";
	cin >> ycoord2;

	if (xcoord2 == xcoord1) {
        cout << "Error: The x-coordinates are the same. Slope is undefined (vertical line).\n";
    } else {
        slope = (ycoord2 - ycoord1) / (xcoord2 - xcoord1);
        cout << "The slope of the line between Points 1 and 2 is " << slope << "\n";
    }

	return 0;
}
