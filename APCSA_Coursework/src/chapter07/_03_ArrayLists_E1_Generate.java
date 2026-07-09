package chapter07;

import java.util.ArrayList;

public class _03_ArrayLists_E1_Generate {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		
		// 1. finish the method randomIntArrayList()
		// 2. use randomIntArrayList() to populate list with 10 integers in [1, 99]
		
		randomIntArrayList(list, 10, 1, 99);
		
		System.out.println(list);
		
	}
	
	/**
	 * populates list with n integers in range [min, max]
	 * @param list : reference to list to be populated
	 * @param n : how many integers to append
	 * @param min
	 * @param max
	 */
	public static void randomIntArrayList(ArrayList<Integer> list, int n, int min, int max) {
		for(int i = 0; i < n; i++)
		{
			int r = (int) (Math.random() * (max-min+1) + min);
			list.add(r);
		}
	}

}
