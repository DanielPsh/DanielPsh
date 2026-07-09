package chapter07;

import java.util.ArrayList;

public class _11_ArrayLists_E7_RemoveZeros {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(0);
		list.add(0);
		list.add(1);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(1);
		System.out.println("original:\t"+list);
		
		// 1. finish method removeZeros()
		// 2. use this to print a randomized version of list
		removeZeros(list);
		System.out.println("after removing zeros :\n\t\t"+list);

	}
	
	/**
	 * removes all zeroes from list
	 * @param list
	 */
	public static void removeZeros(ArrayList<Integer> list) {
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i) == 0)
			{
				list.remove(i);
				i--;
				System.out.println(list);
			}
		}
	}
	


}
