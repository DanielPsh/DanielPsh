package chapter07;

import java.util.ArrayList;

public class _10_ArrayLists_E6_Randomize {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<10; i++) { 
			list.add(i);
		}
		System.out.println("original:\t"+list);
		
		// 1. finish method randomize()
		// 2. use this to print a randomized version of list
		list = randomize(list);
		System.out.println("randomized:\t"+list);

	}
	
	/**
	 * 
	 * @param list
	 * @return a newly constructed list containing elements of list in random order
	 */
	public static ArrayList<Integer> randomize(ArrayList<Integer> list) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int n = list.size();
		for(int i = 0; i < n; i++)
		{
			int r = (int)(Math.random() * list.size());
			ret.add(list.remove(r));
		}
		return ret;
	}
	


}
