package chapter07;

import java.util.ArrayList;

public class _05_ArrayLists_E3_MinMaxAndIndex {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		
		// 1. Use the randomIntArrayList() in E1 to add 10 integers in [1, 99]
		randomIntArrayList(list, 10, 1, 99);
		System.out.println(list);
		
		// 2. finish method minIndex() to find the index of smallest number in list
		// print the index and the minValue
		int minIndex = minIndex(list);
		System.out.println("minIndex: " + minIndex);
		System.out.println("minValue: " + list.get(minIndex) );
		
		// 3. finish method maxIndex() to find the index of largest number in list
		// print the index and the maxValue
		int maxIndex = maxIndex(list);
		System.out.println("maxIndex: " + maxIndex);
		System.out.println("maxValue: " + list.get(maxIndex) );

	}
	
	/**
	 * @param list
	 * @return : index of the first occurring smallest value in list
	 */
	public static int minIndex(ArrayList<Integer> list) {
		int minIndex=0;
		int minValue = list.get(minIndex);
		for(int i = 0; i < list.size(); i++)
		{
			int cur = list.get(i);
			if(cur < minValue)
			{
				minValue = cur;
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	/**
	 * @param list
	 * @return : index of the first occurring largest value in list
	 */
	public static int maxIndex(ArrayList<Integer> list) {
		int maxIndex=0;
		int maxValue = list.get(maxIndex);
		for(int i = 0; i < list.size(); i++)
		{
			int cur = list.get(i);
			if(cur > maxValue)
			{
				maxValue = cur;
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	// from E1
	public static void randomIntArrayList(ArrayList<Integer> list, int n, int min, int max) {
		for(int i = 0; i < n; i++)
		{
			int r = (int) (Math.random() * (max-min+1) + min);
			list.add(r);
		}
	}

}
