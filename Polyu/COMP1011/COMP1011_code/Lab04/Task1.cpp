#include <iostream>
#include <iomanip>

using namespace std;

int main() {
	const int ARRAY_SIZE = 10;
	int data[ARRAY_SIZE] = { 2, 64, 4, 33, 10, 12, 89, 68, 45, 7 };
	int i, insert;

	cout << "Data items in original order\n";

	for (i = 0; i < ARRAY_SIZE; i++) {
		cout << setw(4) << data[i];
	}// output the original order of the array

	for (int next = 1; next < ARRAY_SIZE; next++) {

		insert = data[next];
		int moveItem = next;

		while ((moveItem > 0) && (data[moveItem - 1] < insert)) {
			data[moveItem] = data[moveItem - 1];
			moveItem--;
		}

		data[moveItem] = insert;
	}
	cout << "\nData items in new order\n";
	for (i = 0; i < ARRAY_SIZE; i++) {
		cout << setw(4) << data[i];
	}

	cout << endl;

	return 0;
}
