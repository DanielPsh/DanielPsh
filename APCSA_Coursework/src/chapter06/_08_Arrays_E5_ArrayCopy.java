package chapter06;

public class _08_Arrays_E5_ArrayCopy {

	public static void main(String[] args) {

		int[] arr1 = { 1, 1, 2, 3, 5, 8, 13 };
		// initialize another array of the same size.
		int[] arr2 = new int[arr1.length];
		
		// 1. finish writing the method arrayCopy()
		// 2. use arrayCopy() to copy the contents of arr1 to arr2
		
		printArray(arr2); // before
		arrayCopy(arr1, arr2);
		printArray(arr2); // after
		
	}
	
	
	/**
	 * copies the contents of array a1 onto array a2
	 * @param a1 : contains numbers to be copied
	 * @param a2 : the array to hold numbers from a1
	 */
	public static void arrayCopy(int[] a1, int[] a2){
		for(int i = 0; i < a1.length; i++)
		{
			a2[i] = a1[i];
		}
	}
	
	public static void printArray(int[] arr) {
		System.out.println(" i : a[i]");
		System.out.println("=========");
		for(int i = 0; i < arr.length; i++) {
			System.out.println( " " + i + " : " + arr[i]);
		}
		System.out.println();
	}

}
