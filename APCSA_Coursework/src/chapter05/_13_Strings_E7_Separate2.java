package chapter05;

public class _13_Strings_E7_Separate2 {

	public static void main(String[] args) {
		
		String str = "aabbccddeef";
		
		// 1. using the method separate() developed in 11, 
		// separate str above.
		// how is the string different this time?
		// why does separate() generate a run time error?
		// 2. modify the method separate() so that no error occurs

		separate(str);
		
	}
	
	/**
	 * prints s two characters per line
	 * @param s
	 */
	public static void separate(String s) {
		// s.length(): n=11
		// i: 0 2 4 6 8 10
		for(int i = 0; i < s.length()-1; i += 2) {
			// i+1
			System.out.println(s.substring(i, i + 2));
		}
		// print the last char?
		if(s.length()%2 == 1) {
			System.out.println( s.substring( s.length()-1 ) );
		}
		
	}

}
