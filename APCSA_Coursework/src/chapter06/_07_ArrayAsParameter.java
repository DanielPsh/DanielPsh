package chapter06;

public class _07_ArrayAsParameter {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; 
	
		// 1. finish writing the method timesTen()
		// 2. use timesTen() to add multiply all elements in arr by 10
		
		// arrays are referenced!!
		
		for(int i = 0; i < arr.length; i++)
		{
			System.out.print(arr[i] + ", ");
		}
		System.out.println();
		
		//
		timesTen(arr);
		
		for(int i = 0; i < arr.length; i++)
		{
			System.out.print(arr[i] + ", ");
		}
		System.out.println();
		
		//
		timesTen(arr);
		
		for(int i = 0; i < arr.length; i++)
		{
			System.out.print(arr[i] + ", ");
		}
		System.out.println();

	}
	
	/**
	 * multiplies all elements in arr by 10
	 * @param arr
	 */
	public static void timesTen(int[] arr) {
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = arr[i] * 10;
		}
	}
	


}
