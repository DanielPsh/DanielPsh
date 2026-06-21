#include <iostream>
using namespace std;

int main()
{
    char blocks[3] = {'A', 'F', 'X'};
    char *ptr = blocks;
    char temp;

    ptr = blocks + 1;
    cout << ptr << endl;
    temp = *ptr;
    cout << temp << endl;
    temp = *(ptr + 1);
    cout << temp << endl;
    
    ptr = blocks;
    cout << ptr << endl;
    temp = *++ptr;
    cout << temp << endl;
    temp = ++*ptr;
    cout << temp << endl;
    temp = *ptr++;
    cout << temp << endl;
    temp = *ptr;
    cout << temp << endl;
}