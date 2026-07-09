package chapter01;

public class Modulo_E1 
{
	public static void main(String[] args) {

		
		/** Write a method that prints all the numbers 
		 * 	that are multiples of 3
		 * 	in the range [ 1, 50 ]
		 */

		int x = 49;
		if(x % 3 == 0)
			System.out.println("multiple of 3");

		for(int i = 1; i <= 50; i++)
		{
			if(i % 3 == 0)
				System.out.println(i);
		}
	}
}
