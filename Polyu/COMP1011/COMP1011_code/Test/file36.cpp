#include <iostream>
using namespace std;

int main()
{
    int a = 10;

    int *aPtr = &a; // a
    int *bPtr = NULL;
    cout << "bPtr is: " << bPtr << " \t, *bPtr is: " << *bPtr << endl;
    bPtr = &a;

    return 0;


}

void insertionSort(int a[], int n)
{
    for(int i = 1; i < n; i++)
    {
        int v = a[i];
        int j = i - 1;
        while(j >= 0 && a[j] > v)
        {
            a[j + 1] = a[j];
            j = j - 1;
        }
        a[j + 1] = v;
    }
}