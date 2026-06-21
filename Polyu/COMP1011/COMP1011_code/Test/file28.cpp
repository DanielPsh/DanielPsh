#include <iostream>
using namespace std;

struct BankAccount
{
    int accountNo;
    double balance;
};

struct BankCustomer
{
    char name[51];
    int age;
    char gender;
    char address[101];
    BankAccount accounts[3];
};

void deposit(BankCustomer&, int, double);
void print(const BankCustomer&);

int main()
{
    BankCustomer bc;
    bc.accounts[0].balance = 12345.6;
}