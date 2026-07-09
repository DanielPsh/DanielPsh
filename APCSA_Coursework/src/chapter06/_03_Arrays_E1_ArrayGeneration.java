package chapter06;

public class _03_Arrays_E1_ArrayGeneration {

	public static void main(String[] args) {

		// declare an integer array of 10 elements
		int[] arr = new int[10];
		
		// traverse and fill the array with the arithmetic sequence 10*n (i.e. 10, 20, 30, ... )
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = (i + 1) * 10;
		}

		// print all elements of arr[]
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println( i + " : " + arr[i]);
		}

	}

}
