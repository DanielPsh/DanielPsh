package chapter08;

import java.util.ArrayList;

public class _12_AlternativeIterations_E7_List2Array {


	public static void main(String[] args) {
		
		ArrayList<Integer> from = new ArrayList<Integer>();
		from.add(1);
		from.add(2);
		from.add(3);
		int[] to1 = new int[2];
		int[] to2 = new int[8];

		// 1. finish the method List2Array()
		// 2. use List2Array() to fit from  to to1 and to2
		List2Array(from, to1);
		printArray(to1);
		List2Array(from, to2);
		printArray(to2);
		
		
	}

	/** 
	 * fits all elements in from into to.
	 * if 'from' is longer than 'to', fill in as much as 'to' can take
	 * if 'from' is shorter than 'to', fill 'to' by repeating 'from'
	 * ex: [1 2 3] => [1 2]
	 * ex2: [1 2 3] => [1 2 3 1 2 3 1 2]
	 * @param from
	 * @param to
	 */
	public static void List2Array(ArrayList<Integer> from, int[] to) {

	}
	
	// from ch.06
	public static void printArray(int[] arr) {
		System.out.println(" i : a[i]");
		System.out.println("=========");
		for(int i=0; i<arr.length; i++) {
			System.out.println( " "+i+ " : " + arr[i]);
		}
		System.out.println();
	}
	

}
