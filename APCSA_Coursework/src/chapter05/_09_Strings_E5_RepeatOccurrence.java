package chapter05;

public class _09_Strings_E5_RepeatOccurrence {

	public static void main(String[] args) {
		
		String str = "first second third";
		
		// str is an instance of "triple", 
		// a string comprised of three words separated by spaces.
		// finish the method separateTriple(), which prints the three words on different lines
		
		separateTriple(str);
		
	}
	
	/**
	 * separates string s into three words
	 * @param s : assumes there are exactly two spaces in the string s
	 */
	public static void separateTriple(String s) 
	{
		int first = s.indexOf(" ");
		int second = s.indexOf(" ", first + 1);
		
		String w1 = s.substring(0, first);
		String w2 = s.substring(first + 1, second);
		String w3 = s.substring(second + 1);
		
		System.out.println(w1);
		System.out.println(w2);
		System.out.println(w3);
	}

}
