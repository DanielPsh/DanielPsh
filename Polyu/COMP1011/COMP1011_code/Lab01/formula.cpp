#include <iostream>

using namespace std;

int main(){
    double radius = 11;
    double pi = 3.14;
    double sphere_volume = (4.0/3.0) * pi * pow(radius, 3);
    double surface_area = 4 * pi * pow(radius, 2);

    cout << "Volume = " << sphere_volume << endl;
    cout << "Area = " << surface_area << endl;

    return 0;

}