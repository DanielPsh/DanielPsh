package chapter08;

public class _13_AlternativeIterations_P1 {

	public static void main(String[] args) {
		
		String str = "aaabbaa";
		
		// 1. finish method findNthOccurrence()
		// 2. use findNthOccurrence() to find 
		// 1st~9th occurrences of "aa" in str.
		for( int i=1; i<=9; i++) {
			System.out.print(findNthOccurrence(str,"aa", i)+"  ");
		}
		System.out.println();
		
	}

	
	/**
	 * note: if target is not found or there are less than n occurrences
	 * of target in str, return -1
	 * @param str
	 * @param target
	 * @param n : n>=1
	 * @return the index of nth occurrence of target in str
	 */
	public static int findNthOccurrence(String str, String target, int n) {

		return 0;
	}


}
