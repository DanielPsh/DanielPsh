package chapter07;

import java.util.ArrayList;

public class _09_DynamicTraversal {

	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<5; i++) {
			list.add(1);
		}
		System.out.println(list);
		
		// list contains five 1s.
		// the following attempts to add a 2 after every 1
		// i.e. 1 2 1 2 1 2 ...
		// but fails. In fact, it enters an infinite loop.
		// fix the following code so that it works!
		/*
		int n = list.size();
		for(int i=0; i<n; i++) {
			list.add(2*i+1, 2);
		}
		System.out.println(list);
		*/
		
		for(int i = 0; i < list.size(); i++)
		{
			list.add(i + 1, 2);
			i++;
		}
		System.out.println(list);
		
	}
	


}
