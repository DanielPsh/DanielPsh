package chapter06;

public class _05_Arrays_E3_MinMax {

	public static void main(String[] args) {

		// declare an integer array of 10 elements
		int[] arr = new int[10];
		
		// traverse and fill the array with random integers in range [0, 99]
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = (int) (Math.random() * 100);
		}

		// print all elements of arr[]
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println( i + " : " + arr[i]);
		}
		
		// find and print the minimum and maximum of all the elements in arr
		int min = Integer.MAX_VALUE; 
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < arr.length; i++)
		{
			int current = arr[i];
			if(current < min) {
				min = current;
			}
			if(current > max) {
				max = current;
			}
		}
		System.out.println("min : " + min);
		System.out.println("max : " + max);
		
	}

}
