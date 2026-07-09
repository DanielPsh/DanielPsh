package chapter06;

public class _13_ArrayConstructionAndReturn {

	public static void main(String[] args) {

		int[] arr;
		
		// 1. finish writing the method randomIntArray()
		// 2. use randomIntArray() to new an array and assign it to arr.
		
		arr = randomIntArray( 10, 1, 6 );
		printArray(arr);
		
	}
	
	/**
	 * 
	 * @param n : size of array to be constructed
	 * @param min 
	 * @param max
	 * @return : size n array of random integers in range [min, max]
	 */
	public static int[] randomIntArray(int n, int min, int max) {
		int[] ret = new int[n];
		for(int i = 0; i < ret.length; i++)
		{
			ret[i] = (int)(Math.random() * (max - min + 1) + min);
		}
		return ret;
	}
	
	public static void printArray(int[] arr) {
		System.out.println(" i : a[i]");
		System.out.println("=========");
		for(int i=0; i<arr.length; i++) {
			System.out.println( " "+i+ " : " + arr[i]);
		}
		System.out.println();
	}

}
