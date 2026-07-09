package chapter05;

public class _03_Strings_E2_Expand {

	public static void main(String[] args) {
		
		String str = "expandthis";
		
		// 1. finish the method expandString()
		// 2. use expandString() to get an expanded version of string str and print it.
		// should print : e x p a n d t h i s 
		
		System.out.println( expandString(str) );
		
	}
	
	/**
	 * expands string s by inserting a space after each character of string s.
	 * @param s : string
	 * @return : expanded version of s
	 */
	public static String expandString(String s) {
		String ret = "";
		
		for(int i = 0; i < s.length(); i++){
			ret += s.substring(i, i + 1);
			ret += " ";
			
			//System.out.println( ret );
		}

		
		return ret;
	}
	// 1. Strings are immutable!!

}
