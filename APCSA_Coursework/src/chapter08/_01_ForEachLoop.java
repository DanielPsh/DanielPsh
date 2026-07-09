package chapter08;

import java.util.ArrayList;

public class _01_ForEachLoop {

	public static void main(String[] args) {
		
		// alternative iterative structures
		// for-each loop
		int[] arr = { 1, 2, 3, 4, 5 };
		

		
		/* the following code results in an error. Why?
		for(int num : arr) {
			System.out.println(arr[num]);
		}
		*/
		
		// for-each loops can be used for Lists as well as arrays
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<5; i++) {
			list.add(i+1);
		}
		System.out.println(list);

		
		// you cannot use for-each loop on strings!
		String str = "Hello";

		
	}

}
