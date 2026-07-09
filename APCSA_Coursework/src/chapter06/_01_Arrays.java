package chapter06;

public class _01_Arrays {

	public static void main(String[] args) {

		// Array declaration
		int[] ints = new int[10];				// an array of ten integers
		double[] doubles = new double[10];		// an array of ten doubles
		char[] chars = new char[10];			// an array of ten characters
		String[] strings = new String[10];		// an array of ten Strings
		
		// Direct declaration & initialization
		int[] moreints = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		
		// the above can only be done in initialization.
		// i.e. the following code will result in error:
		/*
		int[] arr;
		arr = {1, 2, 3};
		 */
				
		// accessing and modifying array elements
		// arrayname[index] 
		System.out.println( moreints[3] );
		moreints[3] += 10;
		System.out.println( moreints[3] );
	}

}
