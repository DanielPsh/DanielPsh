package chapter08;

public class _07_AlternativeIterations_E3_findString {

	public static void main(String[] args) {
		
		String aa = "a0a00a000a";
		String bb = "0b0bb0bbb0";
		
		// finish method findString()
		// use findString() to print: 
		
		// indices of all occurrences of "a" in string aa
		// should print 0 2 5 9
		System.out.println(aa);
		findString(aa, "a");
		// indices of all occurrences of "bb" in string bb
		// should print 3 6 7
		System.out.println(bb);
		findString(bb, "bb");
		
	}

	/**
	 * prints indices of all occurrences of string target in string str
	 * @param str
	 * @param target
	 */
	public static void findString(String str, String target) {

	}

	

}
