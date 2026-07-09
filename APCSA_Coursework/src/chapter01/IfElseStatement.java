package chapter01;

public class IfElseStatement 
{
	public static void main(String[] args)
	{
		/*
		 * If (boolean) {
		 *     BODY1
		 * } else {
		 * 	   BODY2
		 * }
		 */
		
		//variable declarations
		int x;
		
		
		//if-else
		x = 5;
		//change the above value to see what happens
		if(x == 1)
		{
			System.out.println("X is 1");
		} else {
			System.out.println("X is NOT 1");
		}
		
		
		//nested if-else
		if(x == 1)
		{
			System.out.println("X is 1");
		} else {
			if(x == 2) {
				System.out.println("X is 2");
			} else {
				System.out.println("X is ... ");
			}	
		}
		
		//better form of if-else
		if(x == 1)
		{
			System.out.println("X is 1");
		} else if(x == 2) {
			System.out.println("X is 2");
		} else if(x == 3) {
			System.out.println("X is 3");
		} else if(x == 4) {
			System.out.println("X is 4");
		} else {
			System.out.println("X is ... ");
		}	
		
	}
}
