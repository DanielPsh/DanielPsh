#include <iostream>
using namespace std;

bool value_occur(int num[], int start, int end, int findNum)
{
    if(start > end)
        return false;
    if(num[start] == findNum)
        return true;
    return value_occur(num, (start + 1), end, findNum);
}

int main()
{
    int list[5] = {1,2,3,4,5};
    int findNum;
    cout << "Enter a num to find: ";
    cin >> findNum;
    cout << value_occur(list, 0, 4, findNum);
    return 0;
}