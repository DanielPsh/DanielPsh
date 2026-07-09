package chapter07;

import java.util.ArrayList;
import java.util.Arrays;

public class _14_ArrayLists_E10_CompressArray {

	public static void main(String[] args) {

		// the following code generates a random array of integers (0, 1, 2)
		int[] arr = new int[10];
		for(int i=0; i<arr.length; i++) {
			arr[i] = (int) (Math.random()*3);
		}
		printArray(arr);
		
		// 1. convert the array arr into a list.
		ArrayList<Integer> list = arrayToList(arr);
		
		// 2. remove all zeroes from list
		removeZeros(list);
		
		// 3. convert list back to an array
		int[] optimized = listToArray(list);
		
		// print optimized array
		printArray(optimized);
	
	}
	
	/**
	 * 
	 * @param list
	 * @return an array containing all elements of list in identical order
	 */
	public static int[] listToArray(ArrayList<Integer> list) {

		return null;
	}

	/**
	 * 
	 * @param arr
	 * @return an arrayList containing all elements of arr in identical order
	 */
	public static ArrayList<Integer> arrayToList(int[] arr) {

		return null;
	}

	// E7
	public static void removeZeros(ArrayList<Integer> list) {

	}
	
	// ch06
	public static void printArray(int[] arr) {
		System.out.println(" i : a[i]");
		System.out.println("=========");
		for(int i=0; i<arr.length; i++) {
			System.out.println( " "+i+ " : " + arr[i]);
		}
		System.out.println();
	}

}
