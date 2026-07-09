package chapter06;

public class _16_Arrays_E11_Sort {

	public static void main(String[] args) {

		// int[] arr = { 3, 2, 4, 7, 8, 5, 9, 6, 1 };
		int[] arr = randomIntArray(10, 1, 100);
		// 1. finish writing the method arraySort()
		// 2. use arrayShuffle() to return an array created by 'shuffling' arr1 and arr2.
		// the resulting array should be ordered: arr1[0] arr2[0] arr1[1] arr2[1] ... 
		// i.e. we want the result to be [11 21 12 22 13 23 14 24 15 25]
		
		printArray(arr);
		int[] sorted = arraySort(arr);
		System.out.println("sorted version: ");
		printArray(sorted);

	}

	/**
	 * assume all numbers in arr are positive. 
	 * arr must not change.
	 * @param arr
	 * @return : a new array that contains the elements of arr in increasing order
	 */
	public static int[] arraySort(int[] arr) {
		int[] res = new int[arr.length];
		int[] workingCopy = new int[arr.length];
		arrayCopy(arr, workingCopy);
		// find min (yet positive) from workingCopy using arrayMinIndex()
		// put it in res
		// set the min to 0
		for(int i = 0; i < res.length; i++) {
			int minIndex = arrayMinIndex(workingCopy);
			res[i] = workingCopy[minIndex];
			workingCopy[minIndex] = Integer.MAX_VALUE;
		}
		return res;
	}
	
	/**
	 * finds index of minimum nonzero element in arr
	 * @param arr
	 * @return
	 */
	public static int arrayMinIndex(int[] arr) {
		int minValue = Integer.MAX_VALUE; 
		int minIndex = -1;  
		for(int i = 0; i < arr.length; i++) {
			int current = arr[i];
			if(current < minValue) {
				minValue = current;
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	public static int[] randomIntArray(int n, int min, int max) {
		int[] ret = new int[n];
		for(int i = 0; i < ret.length; i++)
		{
			ret[i] = (int)(Math.random() * (max - min + 1) + min);
		}
		return ret;
	}
	
	public static void arrayCopy(int[] a1, int[] a2){
		for(int i = 0; i < a1.length; i++)
		{
			a2[i] = a1[i];
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
