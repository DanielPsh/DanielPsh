#include <iostream>
#include <iomanip>
using namespace std;

int main()
{
    const int MAX_STRINGS = 10;
    const int MAX_LENGTH = 11; // 10 characters + 1 for null terminator
    char strArray[MAX_STRINGS][MAX_LENGTH]; // Array to hold the strings
    int freqArray[MAX_STRINGS] = {0}; // Array to hold frequencies

    // Input 10 strings
    for (int i = 0; i < MAX_STRINGS; i++)
    {
        cout << "Please enter string " << (i + 1) << ": ";
        cin >> strArray[i];
    }

    int distinctCount = 0; // Number of distinct strings

    for (int i = 0; i < MAX_STRINGS; i++)
    {
        bool found = false;
        // Check if the string is already counted
        for (int j = 0; j < distinctCount; j++)
        {
            // Compare strings manually
            bool isEqual = true;
            for (int k = 0; k < MAX_LENGTH; k++) // Fix: Manual string comparison loop
            {
                if (strArray[i][k] != strArray[j][k]) // Compare character by character
                {
                    isEqual = false;
                    break;
                }
            }
            if (isEqual) // If the strings are equal
            {
                freqArray[j]++; // Increment frequency
                found = true;
                break;
            }
        }
        // If not found, add to the distinct count
        if (!found)
        {
            for (int k = 0; k < MAX_LENGTH; k++) // Fix: Manual string copy loop
            {
                strArray[distinctCount][k] = strArray[i][k]; // Copy the string
            }
            freqArray[distinctCount] = 1; // Initialize frequency
            distinctCount++;
        }
    }

    // Printing the strings and their frequencies
    cout << "\nFrequencies of distinct strings:" << endl;
    cout << setw(15) << left << "String" << "Frequency" << endl;
    cout << "------------------------------" << endl;

    for (int i = 0; i < distinctCount; i++)
    {
        cout << setw(15) << left << strArray[i] << freqArray[i] << endl;
    }

    return 0;
}