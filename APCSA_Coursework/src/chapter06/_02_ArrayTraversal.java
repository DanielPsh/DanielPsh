package chapter06;

public class _02_ArrayTraversal {

	public static void main(String[] args) {

		int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 }; 
		
		// length of array n
		System.out.println( arr.length );
		
		// traversal, standard
		// print all elements in arr
		for(int i = 0; i < arr.length; i++)
		{
			System.out.print( arr[i] + " ");
		}
		

	}

}
