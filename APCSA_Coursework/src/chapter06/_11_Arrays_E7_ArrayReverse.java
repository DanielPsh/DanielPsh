package chapter06;

import java.util.Arrays;

public class _11_Arrays_E7_ArrayReverse {

	public static void main(String[] args) {

		int[] arr = { 1, 5, 2, 4, 9, 3, 8, 6, 10, 7 };
		
		// 1. finish writing the method arrayReverse()
		// 2. use arrayReverse() on arr, then print arr. 
		// the elements should print in reverse order.
		
		printArray(arr);
		arrayReverse(arr);
		System.out.println("\nReversed version:");
		printArray(arr);
		
	}
	
	
	/**
	 * reverses the order of elements in arr
	 * @param arr
	 */
	public static void arrayReverse(int[] arr){
		for(int i = 0; i < arr.length/2; i++)
		{
			// swap arr[i] with arr[arr.length-1 -i]
			int temp = arr[i];
			arr[i] = arr [ arr.length-1 -i];
			arr [ arr.length-1 -i] = temp;
			System.out.println(Arrays.toString(arr));
		}
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
