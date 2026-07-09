package chapter07;

import java.util.ArrayList;

public class _07_ArrayLists_E4_ListCopy {

	public static void main(String[] args) {

		ArrayList<Integer> list1, list2;
		
		// 1. Use randomIntArrayList() to create a list of 10 integers in [1, 99]
		// assign this to list1.
		list1 = randomIntArrayList(10, 1, 99);
		System.out.println(list1);
		
		// 2. finish method copyOf() 
		// use copyOf() to create a copy of list1, and assign it to list2
		list2 = copyOf(list1);
		System.out.println(list2);

	}
	
	/**
	 * 
	 * @param list
	 * @return : a newly created list containing the exact copy of list
	 */
	public static ArrayList<Integer> copyOf(ArrayList<Integer> list) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(int i = 0; i < list.size(); i++)
		{
			ret.add(list.get(i));
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
