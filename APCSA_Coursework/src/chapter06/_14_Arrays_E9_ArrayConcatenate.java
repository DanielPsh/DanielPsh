package chapter06;

public class _14_Arrays_E9_ArrayConcatenate {

	public static void main(String[] args) {

		int[] arr1 = { 1, 2, 3, 4, 5 };
		int[] arr2 = { 6, 7, 8, 9, 10 };

		// 1. finish writing the method arrayConcatenate()
		// 2. use arrayConcatenate() to return 
		// an array that contains arr1, then arr2.
		// print this array.
		
		int[] concat = arrayConcatenate(arr1, arr2);
		printArray(concat);
		
	}

	/**
	 * 
	 * @param arr1
	 * @param arr2
	 * @return : array that is has all elements in both arr1 and arr2, in that order.
	 */
	public static int[] arrayConcatenate(int[] arr1, int[] arr2) {
		int[] res = new int[arr1.length + arr2.length];
		for(int i = 0; i < arr1.length; i++)
		{
			res[i] = arr1[i];
		}
		// 0 1 2 3 4 => 5 6 7 8 9
		// k 		 => k+5
		for(int i = 0; i < arr2.length; i++)
		{
			res[i + arr1.length] = arr2[i];
		}
		return res;
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
