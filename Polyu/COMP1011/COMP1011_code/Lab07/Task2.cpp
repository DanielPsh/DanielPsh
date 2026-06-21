#include <iostream>
using namespace std;

char encrypt(char letter, int shift)
{
    if(letter >= 'a' && letter <= 'z')
    {
        return(letter - 'a' + shift) % 26 + 'a';
    }else if(letter >= 'A' && letter <= 'Z'){
        return(letter - 'A' + shift) % 26 + 'A';
    }
    return letter;
}

char decrypt(char letter, int shift)
{
    return encrypt(letter, 26 - (shift % 26));
}

int main()
{
    int key, choice;
    char input;

    cout << "Please choose (1) Encryption or (2) Decryption: ";
    cin >> choice;

    if(choice == 1)
    {
        cout << "Please enter an English letter: ";
        cin >> input;
        cout << "Please enter the key (0 - 25): ";
        cin >> key;
        //function encryption
        //encrypt(input, key);
        
        cout << "After encryption, the letter becomes " << encrypt(input, key) << "." << endl;
    }else if(choice == 2)
    {
        cout << "Please enter an English letter: ";
        cin >> input;
        cout << "Please enter the key (0 - 25): ";
        cin >> key;
        //function decryption

        cout << "After encryption, the letter becomes " << decrypt(input, key) << "." << endl;
    }else{
        cout << "Invalid Input!" << endl;
    }
    return 0;
}