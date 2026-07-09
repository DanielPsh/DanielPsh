package chapter07;

import java.util.ArrayList;

public class _08_ArrayLists_E5_Reverse {

	public static void main(String[] args) {

		ArrayList<Integer> list1, list2;
		
		// 1. Use randomIntArrayList() to create a list of 10 integers in [1, 99]
		// assign this to list1.
		list1 = randomIntArrayList(10, 1, 99);
		System.out.println("list1: "+list1);
		
		// 2. finish method reverseOf() 
		// use reverseOf() to create a reverse copy of list1, and assign it to list2
		list2 = reverseOf(list1);
		System.out.println("list2: "+list2);
		
		// 3. now reverse list1 itself (should be the same as list2 now)
		reverse(list1);
		System.out.println("list1: "+list1);

	}
	
	/**
	 * use swapping logic; swap 0th with n-1th, 1st with n-2th...
	 * @param list
	 * @return : reverses the order of the numbers in list
	 */
	public static void reverse(ArrayList<Integer> list) {
		for(int i = 0; i < list.size()/2; i++)
		{
			// list.get(i) <==> list.get(list.size()-1-i)
			int temp = list.get(i);
			list.set(i, list.get(list.size()-1-i));
			list.set(list.size()-1-i, temp);
		}
	}
	
	/**
	 * 
	 * @param list
	 * @return : a newly created list containing the exact copy of list
	 */
	public static ArrayList<Integer> reverseOf(ArrayList<Integer> list) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		/*
		for(int i = list.size()-1; i >= 0; i--)
		{
			ret.add(list.get(i));
		}
		*/
		for(int i = 0; i < list.size(); i++)
		{
			ret.add(0, list.get(i));
		}
		return ret;
	}
	
	// from 06
	public static ArrayList<Integer> randomIntArrayList(int n, int min, int max) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(int i = 0; i < n; i++)
		{
			int r = (int)(Math.random() * (max-min+1)+min);
			ret.add(r);
		}
		return ret;
	}

}
