#include <iostream>
using namespace std;

struct Card
{
    int number[9];
    char alphabet[4];
    string face[4];

    Card() {
        number[0] = 1; number[1] = 2; number[2] = 3; number[3] = 4;
        number[4] = 5; number[5] = 6; number[6] = 7; number[7] = 8; number[8] = 9;

        alphabet[0] = 'J'; alphabet[1] = 'Q'; alphabet[2] = 'K'; alphabet[3] = 'A';

        face[0] = "Hearts"; face[1] = "Diamonds"; face[2] = "Clubs"; face[3] = "Spades";
    }
};
int main()
{
    Card c;
}