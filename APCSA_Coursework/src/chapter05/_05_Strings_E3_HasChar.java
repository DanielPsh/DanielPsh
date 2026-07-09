package chapter05;

public class _05_Strings_E3_HasChar {

	public static void main(String[] args) {
		
		String str = "findithere";
		
		// 1. finish the method hasChar()
		// 2. use hasChar() to determine if str contains the character a, d, e	
		
		String a = "a";
		String d = "d";
		String e = "e";
		// following should return false, true, true
		System.out.println( hasChar(str, a) ); 
		System.out.println( hasChar(str, d) ); 
		System.out.println( hasChar(str, e) );	
		
	}
	
	/**
	 * determines if string s contains the character represented by string c
	 * @param s
	 * @param c : assume c contains just one character
	 * @return : true if s contains c, false otherwise
	 */
	public static boolean hasChar(String s, String c) {
		boolean ret = false;
		
		for(int i = 0; i < s.length(); i++)
		{
			// ith char in s == c
			if(s.substring(i, i+1).equals(c))
			{
				ret = true;
			}
		}
		return ret;
	}

}
