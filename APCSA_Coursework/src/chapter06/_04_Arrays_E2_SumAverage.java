package chapter06;

public class _04_Arrays_E2_SumAverage {

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
		
		// find and print the sum and average of all the elements in arr
		int sum = 0; 
		for(int i = 0; i < arr.length; i++)
		{
			sum += arr[i];
		}
		System.out.println("sum : " + sum);
		System.out.println("average : " + (double) sum / arr.length);
		
	}

}
