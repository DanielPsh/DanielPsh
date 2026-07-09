package chapter06;

public class _12_Arrays_E8_ArrayRandomize {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

		// 1. finish writing the method arrayReverse()
		// 2. use arrayReverse() on arr, then print arr. 
		// the elements should print in reverse order.
		
		printArray(arr);
		arrayRandomize(arr);
		System.out.println("Randomized version:");
		printArray(arr);
		
	}
	
	
	/**
	 * randomizes the elements in arr. 
	 * there should be no duplicated elements,
	 * and every randomization should result in a different array
	 * @param arr
	 */
	public static void arrayRandomize(int[] arr){
		for(int i = 0; i < arr.length; i++)
		{
			// create a random index r = [0,n)
			// int r = (int) (Math.random() * range + offset);
			int r = (int) (Math.random() * arr.length);
			
			// swap arr[i] with arr[r]
			int temp = arr[i];
			arr[i] = arr[r];
			arr[r] = temp;
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
