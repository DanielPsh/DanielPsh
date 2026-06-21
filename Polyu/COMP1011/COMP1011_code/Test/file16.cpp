#include <iostream>
using namespace std;

int sum(int *a, int *b)
{
	int answer;
	answer = *a + *b;

    *a = 100;
    *b = 60;
    return answer;
    //return 0;
}
int main()
{
	int number1, number2;
	cin >> number1 >> number2;
	cout << number1 << " " << number2 << endl;
	
    cout << "number1 is " << number1 << endl;
    cout << "number2 is " << number2 << endl;
	cout << "The sum is " << sum(&number1, &number2) << endl;
    cout << "number1 is " << number1 << endl;
    cout << "number2 is " << number2 << endl;
	
	return 0;
}