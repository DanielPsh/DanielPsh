#include <iostream>
using namespace std;

int main()
{
    //Question 1
    cout << "Student ID: 24101315d" << endl;
    cout << "Student Name: PARK SANGHYUN\n" << endl;

    //Question 2

    //declare
    int choice;
    double mass, velocity, height, force;
    const double gravity = 9.81;
    //user input
    cout << "Basic Physics Calculator" << endl;
    cout << "===== MENU =====" << endl;
    cout << "[1] Calculate Kinetic Energy (KE = 0.5 x mass x velocity^2)\n[2] Calculate Potential Energy (PE = mass x gravity x height)\n[3] Calculate Momentum (Momentum = mass x velocity)\n[4] Calculate Acceleration (Acceleration = force / mass)\nEnter your choice (1-4): ";
    cin >> choice;

    if(choice == 1)//Calculate Kinetic
    {
        //declare
        double kinetic;
        //user input
        cout << "Enter the mass (in kg): ";
        cin >> mass;
        cout << "Enter the velocity (in m/s): ";
        cin >> velocity;
        //calculate
        kinetic = 0.5 * mass * velocity * velocity;
        //display
        cout << "The kinetic energy is: " << kinetic << " Joules" << endl;

    }else if(choice == 2)//Calculate Potential
    {
        //declare
        double potential;
        //user input
        cout << "Enter the mass (in kg): ";
        cin >> mass;
        cout << "Enter the height (in m): ";
        cin >> height;
        //calculate
        potential = mass * gravity * height;
        //display
        cout << "The potential energy is: " << potential << " Joules" << endl;

    }else if(choice == 3)//Calculate Momentum
    {
        //declare
        double momentum;
        //user input
        cout << "Enter the mass (in kg): ";
        cin >> mass;
        cout << "Enter the velocity (in m/s): ";
        cin >> velocity;
        //calculate
        momentum = mass * velocity;
        //display
        cout << "The momentum is: " << momentum << " kg * m/s" << endl;

    }else if(choice == 4)//Calculate Acceleration
    {
        //declare
        double acceleration;
        //user input
        cout << "Enter the mass (in kg): ";
        cin >> mass;
        cout << "Enter the force (in N): ";
        cin >> force;
        //Zero-cases
        if(mass > 0)
        {
            //calculate
            acceleration = force / mass;
            //display
            cout << "The acceleration is: " << acceleration << " m/s^2" << endl;

        } else {
            cout << "Wrong Input";
        }
    }else{
        cout << "Wrong Input";
    }

    return 0;
}