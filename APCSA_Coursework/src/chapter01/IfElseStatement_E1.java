package chapter01;

public class IfElseStatement_E1 
{
	public static void main(String[] args) {

		// variable declarations
		int x;
		
		x = 1;	// <== change this and test your code
		
		/** write a routine that prints
		 * "positive" when x is greater than 0,
		 * "negative" when x is less than 0
		 * "zero" when x is zero
		 */
		if(x > 0)
		{
			System.out.println("positive");
		} else if (x < 0) {
			System.out.println("negative");
		} else if (x == 0) {
			System.out.println("zero");
		}


	}
}
