package chapter01;

public class Modulo 
{
	public static void main(String[] args) {

		// variable declarations
		int num, div, q, r, x;

		num = 15;
		div = 7;
		// find and print the quotient and remainder when num is divided by div
		q = num / div;
		System.out.println("quotient: " + q);
		r = num % div;
		System.out.println("remainder: " + r);
		//15 = 7 * 2 + 1 
		
		
		x = 1234567;
		System.out.println("x = " + x);
		
		// find out and print if x is even or odd
		if(x % 2 == 0)
			System.out.println("x is even");
		else 
			System.out.println("x is odd");
		
		// print the units digit of x
		// 123456 = 12345 * 10 + 6
		System.out.println("units digit of x: " + x % 10);

		// print the last three digits of x
		// 123456 = 123 * 1000 + 456
		System.out.println("last three digits of x: " + x % 1000);
	
		// print the tens digit of x
		System.out.println("tens digit of x: " + x/10 % 10);
		
		// print the hundreds digit of x
				System.out.println("hundreds digit of x: " + x/10/10 % 10);

		
	}
}
