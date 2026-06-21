#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    double currentSalary;
    double annualIncreaseRate = 0.05;
    int years = 10;
    double futureSalary;

    cout << "What is your current Salary: ";
    cin >> currentSalary;

    futureSalary = currentSalary * pow(1 + annualIncreaseRate, years);

    cout << "After " << years << " years, your salary is " << futureSalary << endl;

    return 0;
    
}