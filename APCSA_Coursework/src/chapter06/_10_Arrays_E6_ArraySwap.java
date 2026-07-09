package chapter06;

public class _10_Arrays_E6_ArraySwap {

	public static void main(String[] args) {

		int[] arr1 = { 1,1,1,1,1 };
		int[] arr2 = { 2,2,2,2,2 };
		
		// 1. finish writing the method arraySwap()
		// 2. use arraySwap() to swap the contents of arr1 and arr2
		
		printArray(arr1);
		printArray(arr2);
		arraySwap(arr1, arr2);
		System.out.println("After swapping...");
		printArray(arr1);
		printArray(arr2);
		
	}
	
	

	/** 
	 * swaps the contents of a1 and a2
	 * @param a1
	 * @param a2
	 */
	public static void arraySwap(int[] a1, int[] a2){
		for(int i = 0; i < a1.length; i++)
		{
			int temp = a1[i];
			a1[i] = a2[i];
			a2[i] = temp;
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
