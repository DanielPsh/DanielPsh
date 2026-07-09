package chapter06;

public class _15_Arrays_E10_ArrayShuffle {

	public static void main(String[] args) {

		int[] arr1 = { 11, 12, 13, 14, 15 };
		// 0 2 4 6 8 => 2 * i
		int[] arr2 = { 21, 22, 23, 24, 25 };
		// 1 3 5 7 9 => 2 * i + 1
		// 1. finish writing the method arrayShuffle()
		// 2. use arrayShuffle() to return an array created by 'shuffling' arr1 and arr2.
		// the resulting array should be ordered: arr1[0] arr2[0] arr1[1] arr2[1] ... 
		// i.e. we want the result to be [11 21 12 22 13 23 14 24 15 25]
		// 								  0	 1  2  3  4  5  6  7  8  9 
		int[] shuffled = arrayShuffle(arr1, arr2);
		printArray(shuffled);

	}

	/**
	 * assume arr1 and arr2 have the same length
	 * @param arr1
	 * @param arr2
	 * @return : array that is has all elements in both arr1 and arr2, in that order.
	 */
	public static int[] arrayShuffle(int[] arr1, int[] arr2) {
		int[] res = new int[arr1.length + arr2.length];
		// arr1: 2*i
		for(int i = 0; i < arr1.length; i++)
		{
			res[2*i] = arr1[i];
			res[2*i + 1] = arr2[i];
		}
		// arr2: 2*i + 1
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
