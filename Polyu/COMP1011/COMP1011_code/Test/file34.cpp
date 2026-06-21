#include <iostream>
using namespace std;

// Function prototype
void rotate(char *charArray, int *sizeOfArray);

int main() {
    char charArray[50]; // Array to store up to 50 characters
    cout << "Enter a series of characters (max 50): ";
    cin.getline(charArray, 50); // Read a line of characters

    int sizeOfArray = strlen(charArray); // Get the size of the input

    // Rotate the array 3 times
    for (int i = 0; i < 3; i++) {
        rotate(charArray, &sizeOfArray);
        cout << charArray << endl; // Output the result after each rotation
    }

    return 0;
}

// Function to rotate the characters
void rotate(char *charArray, int *sizeOfArray) {
    // Store the last character
    char lastChar = *(charArray + (*sizeOfArray - 1));
    
    // Shift all characters to the right
    for (int i = *sizeOfArray - 1; i > 0; i--) {
        *(charArray + i) = *(charArray + i - 1);
    }
    
    // Place the last character at the start
    *charArray = lastChar;
}