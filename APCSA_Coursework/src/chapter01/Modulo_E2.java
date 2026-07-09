package chapter01;

public class Modulo_E2 
{
	public static void main(String[] args) {

		// variable declarations
		int year;

		year = 2000;
		
		/** Write a method that checks if a given year is a leap year.
		 * If a year is divisible by 4, it is a leap year, unless it is divisible by 100.
		 * If a year is divisible by 400, it is a leap year.
		 */
		boolean isLeap = false;
		
		if(year % 4 == 0 && (year % 100 == 0) || year % 400 == 0)
				isLeap = true;
		
		
		
		
		
		if (isLeap) {
			System.out.println(year + " is a leap year.");
		} else {
			System.out.println(year + " is not a leap year.");
		}
		

		
	}
}
