#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
    char uppercaseLetter[26];
    char lowercaseLetter[26];
    char digit[10];
    
    lowercaseLetter[0] = 'a';
    uppercaseLetter[0] = 'A';
    digit[0] = '0';

    cout << setw(15) << "Characters" << setw(10) << "ASCII" << endl;

    //lowercase
    for(int i = 0; i < 26; i++)
    {
        lowercaseLetter[i] = lowercaseLetter[0] + i;
        cout << setw(15) << lowercaseLetter[i];
        cout << setw(10) << (int)lowercaseLetter[i] << endl;
    }

    //uppercase
    for(int i = 0; i < 26; i++)
    {
        uppercaseLetter[i] = uppercaseLetter[0] + i;
        cout << setw(15) << uppercaseLetter[i];
        cout << setw(10) << (int)uppercaseLetter[i] << endl;
    }

    //digit
    for(int i = 0; i < 26; i++)
    {
        digit[i] = digit[0] + i;
        cout << setw(15) << digit[i];
        cout << setw(10) << (int)digit[i] << endl;
    }
    return 0;
}