package chapter08;

import java.util.ArrayList;

public class _02_ForEachLoopLimitation {

	public static void main(String[] args) {
		
		// for each loops have limitations
		int[] arr = { 1, 2, 3, 4, 5 };
		printArray(arr);
		// the following is an attempt to add 10 to each element of arr
		for(int num : arr) {
			num += 10;
		}
		System.out.println("After adding 10...");
		// nothing changes as a result. why?
		printArray(arr);
		

		// same thing happens with arrays.
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<5; i++) {
			list.add(i+1);
		}
		System.out.println(list);
		for(int num : list) {
			num += 10;
		}
		System.out.println("After adding 10...");
		System.out.println(list);
		
	}
	
	public static void printArray(int[] arr) {
		for(int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

}
