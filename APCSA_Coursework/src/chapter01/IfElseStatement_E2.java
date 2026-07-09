package chapter01;

public class IfElseStatement_E2 
{
	public static void main(String[] args) {

		// variable declarations
		int x;
		
		x = -1;	// <== change this and test your code
		
		/** write a routine that prints
		 * the absolute value of x
		 * do NOT use Math.abs()!! 
		 */
		if (x < 0)
		{
			System.out.print(-x + " is the absolute value");
		}
		else if(x > 0)
		{
			System.out.print(x + " is the absolute value");
		}
		else 
			System.out.print("Undefined");
	}
}
