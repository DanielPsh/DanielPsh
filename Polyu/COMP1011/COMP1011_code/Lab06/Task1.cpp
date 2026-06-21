#include <iostream>
using namespace std;

int sum(int a, int b)
{
    int sum = 0;

    sum = a + b;

    return sum;
}
double absolute(double num)
{
    if(num < 0)
    {
        return -num;
    }else{
        return num;
    }
}

int pow(int base, int exp)
{
    int result = 1;
    for(int i = 0; i < exp; i++)
    {
        result = result * base;
    }
    return result;
}


// Ceil function (rounds up to the nearest integer)
int ceilValue(double num)
{
    int intPart = static_cast<int>(num); // Get the integer part of the number
    if (num > intPart)                  // Check if there's a decimal part
    {
        return intPart + 1;             // Round up
    }
    else
    {
        return intPart;                 // Return the integer part
    }
}

// Renamed Floor function to "floorValue" to avoid conflicts
int floorValue(double num)
{
    int intPart = static_cast<int>(num); // Get the integer part of the number
    if (num < intPart)                   // For negative numbers
    {
        return intPart - 1;              // Round down
    }
    else
    {
        return intPart;                  // Return the integer part
    }
}

double sqrt(unsigned int num)
{
    double low = 0, high = num, mid;
    while (high - low > 0.01) {
        mid = (low + high) / 2;
        if (mid * mid < num) {
            low = mid;
        } else {
            high = mid;
        }
    }
    return (low + high) / 2;
}

bool isPrime(unsigned int num)
{
    if(num <= 1)
    {
        return false;
    }
    for(unsigned int i = 2; i * i <= num; i++)
    {
        if(num % i == 0)
        {
            return false;
        }
    }
    return true;
    
}


int main()
{
    cout << "Sum: " << sum(3, 7) << endl;
    cout << "Abs: " << absolute(-5.0) << endl;
    cout << "Pow: " << pow(2, 3) << endl;
    cout << "Ceil: " << ceilValue(5.3) << endl;
    cout << "Floor: " << floorValue(5.8) << endl;
    cout << "Square root: " << sqrt(16) << endl;
    if (isPrime(7)) {
        cout << "Is Prime: Yes" << endl;
    } else {
        cout << "Is Prime: No" << endl;
    }
    return 0;
}