package chapter05;

public class _02_Strings_E1_Traversal {

	public static void main(String[] args) {
		
		String str = "Better When Vertical";
		
		// 1. finish the method printVertical()
		// 2. use printVertical() to print str vertically
/*
l
i
k
e

t
h
i
s
 */

		printVertical(str);
		
	}
	
	/**
	 * prints string s so that each character is printed in a new line
	 * @param s
	 */
	public static void printVertical(String s) {
		// traverse s 
		for(int i = 0; i < s.length(); i++){
			System.out.println( s.substring(i, i + 1) );
		}

	}

}
