#include <iostream>
#include <cstdlib> //rand
using namespace std;

int main()
{
    int compChoice, userChoice;
    char playAgain;
    do{
        compChoice = rand() % 3 + 1;//random num 1-3

        // Validate input
        if (userChoice < 1 || userChoice > 3) {
            cout << "Invalid choice. Please try again.\n";
            continue; // Restart the loop for valid input
        }

        //display choices
        cout << "Please choose Rock(1), Paper(2) or Scissors(3): ";
        cin >> userChoice;

        if (compChoice == userChoice)
        {
            cout << "Draw." << endl;
        }else if(userChoice == 1 && compChoice == 3 || userChoice == 2 && compChoice == 1 || userChoice == 3 && compChoice == 2)
        {
            cout << "You Win." << endl;
        }else{
            cout << "Computer Wins." << endl;
        }



        // Ask if player wants to play again
        cout << "Do you want to play again? (y/n): ";
        cin >> playAgain;

    }while(playAgain == 'y' || playAgain == 'Y');

    cout << "Thanks for playing!" << endl;
    return 0;
}