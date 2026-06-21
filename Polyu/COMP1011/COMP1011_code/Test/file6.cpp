#include <iostream>
using namespace std;
int main()
{
	int n;
	int sum = 0;
    cout<<"input: ";
	cin >> n;
	
	for (int i = 1; i <= n; i++)
	{
		cout << sum + i * i << endl;
		sum = sum + i * i;
	}
	return 0;
}