package chapter07;

import java.util.ArrayList;

public class _06_ArrayListConstructionAndReturn {

	public static void main(String[] args) {

		ArrayList<Integer> list;
		
		// modify randomIntArrayList() to return a new list
		// use it to generate a list of 10 integers in [1, 99]
		list = randomIntArrayList(10, 1, 99);
		System.out.println(list);

	}
	
	/**
	 * 
	 * @param n
	 * @param min
	 * @param max
	 * @return : a newly constructed ArrayList of n Integers in range [min, max]
	 */
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
