#include <iostream>
using namespace std;

int countElement(int list[], int len)
{
    int count = 0;
    for(int i = 0; i < len; i++)
    {
        if(list[i] == 0)
        {
            return 0;
        }
        count++;
    }
    return count;
}

int countElement_recur(int num[], int start, int end)
{
    if(start > end)
        return 0;
    return 1 + countElement_recur(num, (start + 1), end);
}

int main()
{
    int list[5] = {1,2,3,4,5};
    int len = countElement(list, 5);
    int len1 = countElement_recur(list, 0, 4);
    cout << len << endl;
    cout << len << endl;
    return 0;
}