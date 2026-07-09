package chapter07;

import java.util.ArrayList;

public class _04_ArrayLists_E2_SumAverage {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		
		// 1. Use the randomIntArrayList() in E1 to add 10 integers in [1, 99]
		randomIntArrayList(list, 10, 1, 99);
		System.out.println(list);
		
		// 2. finish method listSum() to find the sum of numbers in list
		int sum = listSum(list);
		
		// 3. print the sum and average.
		System.out.println("sum: " + sum);
		System.out.println("average: " + (double) sum / list.size() );
		
	}
	
	/**
	 * 
	 * @param list
	 * @return : sum of all numbers in list
	 */
	public static int listSum(ArrayList<Integer> list) {
		int sum = 0;
		for(int i = 0; i < list.size(); i++)
		{
			sum += list.get(i);
		}
		return sum;
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
