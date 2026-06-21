#include <iostream>
using namespace std;

int largestNumber(int num[], int size)
{
    int largestNumber;
    for(int i = 0; i < size; i++)
    {
        if(num[i] > num[i +1 ])
        {
            largestNumber = num[i];
        }
    }
    return largestNumber;
}
int smallestNumber(int num[], int size)
{
    int smallestNumber = num[0];
    for(int i = 0; i < size; i++)
    {
        if(num[i] < smallestNumber)
        {
            smallestNumber = num[i];
        }
    }
    return smallestNumber;
}
double total(int num[], int size)
{
    double total;
    for(int i = 0; i < size; i++)
    {
        total = total + num[i];
    }
    return total;
}
double average(int num[], int size)
{
    double avg;
    double sum = 0;
    for(int i = 0; i < size; i++)
    {
        sum = sum + num[i];
    }
    avg = sum / size;
    return avg;
}
int main()
{
    int size = 5;
    int num[size];
    for(int i = 0; i < size; i++)
    {
        cout << "Enter number " << i + 1 << ": ";
        cin >> num[i];
    }

    cout << "The largest number from the list is: " << largestNumber(num, size) << endl;

    cout << "The smallest number from the list is: " << smallestNumber(num, size) << endl;

    cout << "The total: " << total(num, size) << endl;

    cout << "The average: " << average(num, size) << endl;
    return 0;
}