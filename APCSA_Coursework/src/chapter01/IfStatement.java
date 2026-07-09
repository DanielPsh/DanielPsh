package chapter01;

public class IfStatement 
{
	public static void main(String[] args)
	{
		/*
		 * IF(boolean){
		 * 	  BODY
		 * }
		 */
		
		//variable declarations
		boolean state;
		int x;
		
		x = 1;
		//change the above value to see what happens
		state = x == 1;
		if(state)
		{
			System.out.println("X is 1");
		}
		
		
		//you can do this directly
		if(x == 1)
		{
			System.out.println("X is 1");
		}
	}
}
