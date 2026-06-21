#include <iostream>
using namespace std;

bool isPalindrome(const char str[], int length)
{
    char filter[100];
    int index = 0;
    if(str[length] == 0)
    {
        cout << "Bye!" << endl;
        return 1;
    }
    
    for(int i = 0; i < length; i++)
    {
        char ch = str[i];
        if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
        {
            if((ch >= 'A' && ch <= 'Z'))
            {
                ch = ch + ('a' - 'A');
            }
            filter[index++] = ch;
        }
    }

    for (int left = 0, right = index - 1; left < right; ++left, --right) {
        if (filter[left] != filter[right]) {
            return false; // Not a palindrome
        }
    }
    return true; // It is a palindrome

}

int main()
{
    char str1[101];

    cout << "Enter a string: ";
    cin >> str1;

    int length = 0;
    while (str1[length] != '\0') 
    {
        length++;
    }

    if(length > 100)
    {
        cout << "Limit Exceeds!" << endl;
        return 1;
    }

    if(isPalindrome(str1, length))
    {
        cout << "true" << endl;
    }else{
        cout << "false" << endl;
    }

    return 0;
}