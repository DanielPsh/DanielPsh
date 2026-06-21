#include <iostream>
#include <cstdlib> //rand() and srand()
#include <ctime> //time()
using namespace std;

int main()
{
    int playAgain;

    // Seed the random number generator
    srand(static_cast<unsigned int>(time(0)));
    
    
    do{
        int diceResult = (rand() % 6) + 1; // 1-6
        cout << " ===== Welcome to the Number Guessing Game! =====" << endl;
        
        // Human guess
        int humanGuess;
        cout << "Enter your guess (1 to 6), 0 to exit: ";
        cin >> humanGuess;
        if(humanGuess == 0)
        {
            playAgain = 0;
            cout << "Thank you for playing." << endl;
            break;
        }
        // Computer guess
        int computerGuess = (rand() % 6) + 1;
        cout << "Computer's guess: " << computerGuess << endl;
        
        int humanDifference = abs(diceResult - humanGuess);
        int computerDifference = abs(diceResult - computerGuess);

        cout << "The dice rolled: " << diceResult << endl;

        if(humanDifference < computerDifference)
        {
            cout << "Human wins!" << endl;
        }else if(humanDifference > computerDifference)
        {
            cout << "Computer wins!" << endl;
        }else{
            cout << "It's a draw!" << endl;
        }

        
    }while(playAgain != 0);

    return 0;
}