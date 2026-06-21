#include <iostream>
using namespace std;

bool isPutInDeanList(double g, int n) {

	if (g >= 4.0 && n >= 3) {
		return true;
	}
	return false;
}
int main() {

	double gpa;
	int noOfSubjects;
	
	cout << "What is your GPA: ";
	cin >> gpa;
	cout << "How many subjects did you take in the last semester? ";
	cin >> noOfSubjects;
	
	if (isPutInDeanList(gpa, noOfSubjects)) {
		cout << "You are put in the Dean's list." << endl;
	} else {
	cout << "Study Harder!" << endl;
	}
	
	return 0;
}