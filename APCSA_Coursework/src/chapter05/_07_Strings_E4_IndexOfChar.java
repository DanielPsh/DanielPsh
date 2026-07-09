package chapter05;

public class _07_Strings_E4_IndexOfChar {

	public static void main(String[] args) {
		
		String str = "abcdefghijklmnopqrstuvwxyzc";
		
		// 1. finish the method indexOfChar()
		// 2. use indexOfChar() to how many letters of the alphabet come before: c, j, z
		String c = "c";
		String j = "j";
		String z = "z";
		String sharp = "#";
		// following should print: 2, 9, 25, -1
		System.out.println( indexOfChar(str, c) ); 
		System.out.println( indexOfChar(str, j) ); 
		System.out.println( indexOfChar(str, z) );	
		System.out.println( indexOfChar(str, sharp) );	
		
	}
	
	/**
	 * @param s : string to search in.
	 * @param c : character(in string form) to search for.
	 * @return : if s contains c, return the index of the first occurrence of c. 
	 * 			otherwise return -1.
	 */
	public static int indexOfChar(String s, String c) 
	{
		for(int i = 0; i < s.length(); i++)
		{
			if(s.substring(i, i + 1).equals(c))
			{
				return i;
			}
		}
		return -1;
	}
	// use break!
	// or, use premature returns
}
