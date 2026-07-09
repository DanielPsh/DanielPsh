package chapter07;

import java.util.ArrayList;

public class _13_ArrayLists_E9_RemoveOdd {

	public static void main(String[] args) {

		ArrayList<Integer> list;
		
		// 1. Use randomIntArrayList() to create a list of 10 integers in [1, 20]
		// assign this to list.
		list = randomIntArrayList(10, 1, 20);
		System.out.println("list: "+list);

		// 2. finish method removeEven()
		// use removeEven() to remove all even numbers from list
		// print the revised list and how many even numbers were removed
		ArrayList<Integer> odds = removedOdds(list);
		System.out.println("remaining even numbers: "+list);
		System.out.println("removed odd numbers: "+odds);

	}
	
	/**
	 * removes all odd numbers from list, then returns a new list of the removed numbers
	 * @param list
	 */
	public static ArrayList<Integer> removedOdds(ArrayList<Integer> list) {
		int count = 0;
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i)%2==1)
			{
				list.remove(i);
				i--;
				count++;
			}
		}
		return null;
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
