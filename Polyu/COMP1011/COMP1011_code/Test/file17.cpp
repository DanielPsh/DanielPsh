#include <iostream>
using namespace std;

void swap(int& a, int& b)
{
    int temp;
    temp = a;
    a = b;
    b = temp;
} //call by reference
void sort(int numarry[], int size)
{
    for(int i = 0; i < size - 1; i++)
    {
        for(int j = 0; j < size - 1; j++)
        {
            if(numarry[j] > numarry[j + 1])
            {
                swap(numarry[j], numarry[j + 1]);
            }
        }
    }
} //call-by-value

int main()
{
    int num[5] = {7,6,5,4,3};
    sort(num, 5);
    cout << "The sorted array is ";
    for(int i = 0; i < 5; i++)
    {
        cout << " " << num[i];
    }
    cout << endl;
    return 0;
}