package chapter05;

public class _16_Strings_E9_AlphabeticalOrder {

	public static void main(String[] args) {
		
		// 1. finish the method isInAscendingOrder()
		// 2. use isInAscendingOrder() to determine whether the following strings are 
		// in ascending order:

		String s1 = "abcdef";
		String s2 = "abcedf";
		String s3 = "aaaaaa";
		// true, false, false (s3 is technically nondescending) 
		System.out.println( isInAscendingOrder(s1) );
		System.out.println( isInAscendingOrder(s2) );
		System.out.println( isInAscendingOrder(s3) );
		
	}
	
	/** 
	 * determines if string s is in ascending order alphabetically
	 * @param s
	 * @return 
	 */
	public static boolean isInAscendingOrder(String s) {
		boolean ret = true;
		for(int i = 0; i < s.length()-1; i++)
		{
			String one = s.substring(i, i + 1);
			String two = s.substring(i + 1, i + 2);
			if( one.compareTo(two) >= 0)
			{
				ret = false;
			}
		}
		
		return ret;
	}

}
