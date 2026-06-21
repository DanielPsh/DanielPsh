#include <iostream>
using namespace std;
double average(double arr[], int size)
{
    int sum = 0;
    double avg;
    for(int i = 0; i < size; i ++)
    {
        sum = sum + arr[i];
        avg = sum / size;
    }
    return avg;
}
int main() {
    double data[] = {1,2,3,4,5};

    cout << "average: " << average(data, 5);

    return 0;
}