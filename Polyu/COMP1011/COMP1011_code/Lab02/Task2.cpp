#include <iostream>

using namespace std;
int main()
{
    //Declare
    int num1, num2, num3;
    int sum = 0;
    double avg;
    int product;

    //Input
    cout << "Input Integer: ";
    cin >> num1;

    cout << "Input Integer: ";
    cin >> num2;

    cout << "Input Integer: ";
    cin >> num3;

    //Intialize
    int smallest = num1;
    int largest = num1;

    //Sum
    sum = num1 + num2+ num3;
    cout << "Sum is: " << sum << endl;

    //Average
    avg = sum / 3.0;
    cout << "Average is: " << avg << endl;

    //Product
    product = num1 * num2 * num3;
    cout << "Product is: " << product << endl;

    //Smallest
    if(num2 < smallest)
        smallest = num2;
    if(num3 < smallest)
        smallest = num3;
    cout << "Smallest is: " << smallest << endl;

    //Largest
    if(num2 > largest)
        largest = num2;
    if(num3 < largest)
        largest = num3;
    cout << "Largest is: " << largest << endl;

    return 0;
} // namespace std

//BEGIN
//    DECLARE three integers: num1, num2, num3
//    DECLARE integer sum, product, largest, smallest
//    DECLARE float average
//
//    PRINT "Input three different integers: "
//    READ num1, num2, num3
//
//    SET sum = num1 + num2 + num3
//    SET average = sum / 3.0
//    SET product = num1 * num2 * num3
//
//    SET smallest = num1
//    SET largest = num1
//
//    IF num2 < smallest THEN
//        SET smallest = num2
//    ENDIF
//
//    IF num2 > largest THEN
//        SET largest = num2
//    ENDIF
//
//    IF num3 < smallest THEN
//        SET smallest = num3
//    ENDIF
//
//    IF num3 > largest THEN
//        SET largest = num3
//    ENDIF
//
//    PRINT "Sum is ", sum
//    PRINT "Average is ", average
//    PRINT "Product is ", product
//    PRINT "Smallest is ", smallest
//    PRINT "Largest is ", largest
//END