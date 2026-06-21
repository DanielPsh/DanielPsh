#include <iostream>
#include <iomanip>
using namespace std;
int main()
{
	int initial_balance = 10000;
	double interest_rate = 0.05;
	int nyears;
	double balance;
	
	cout << "Enter the number of years: ";
	cin >> nyears;
	
	balance = initial_balance;
	
    cout << fixed << setprecision(2);
	for(int i = 1; i <= nyears; i++)
	{
		double interest = balance * interest_rate;
		balance = balance + interest;
		cout << setw(4) << i << setw(10) << balance << endl;
	}
	return 0;
}