package chapter06;

public class _06_Arrays_E4_MinMaxIndex {

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
		
		// find and print the index of the min and max in arr
		int minValue = Integer.MAX_VALUE; 
		int maxValue = Integer.MIN_VALUE;
		int minIndex = -1; 
		int maxIndex = -1; 
		for(int i = 0; i < arr.length; i++)
		{
			int current = arr[i];
			if(current < minValue) {
				minValue = current;
				minIndex = i;
			}
			if(current > maxValue) {
				maxValue = current;
				maxIndex = i;
			}
		}
		System.out.println("min : " + minValue);
		System.out.println("minIndex : " + minIndex);
		System.out.println("max : " + maxValue);
		System.out.println("maxIndex : " + maxIndex);
		
	}

}
