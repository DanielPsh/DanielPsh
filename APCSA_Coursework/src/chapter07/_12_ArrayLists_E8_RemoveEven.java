package chapter07;

import java.util.ArrayList;

public class _12_ArrayLists_E8_RemoveEven {

	public static void main(String[] args) {

		ArrayList<Integer> list;
		
		// 1. Use randomIntArrayList() to create a list of 10 integers in [1, 20]
		// assign this to list.
		list = randomIntArrayList(10, 1, 20);
		System.out.println("list: "+list);

		// 2. finish method removeEven()
		// use removeEven() to remove all even numbers from list
		// print the revised list and how many even numbers were removed
		int n = removeEven(list);
		System.out.println("after removing "+n+" even numbers: "+list);

	}
	
	/**
	 * removes all even numbers from list
	 * @param list
	 */
	public static int removeEven(ArrayList<Integer> list) {
		int count = 0;
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i)%2==0)
			{
				list.remove(i);
				i--;
				count++;
			}
		}
		return count;
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
