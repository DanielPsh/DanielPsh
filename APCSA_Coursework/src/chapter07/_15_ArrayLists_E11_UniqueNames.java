package chapter07;

import java.util.ArrayList;

public class _15_ArrayLists_E11_UniqueNames {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<String>();
		list.add("Abe");
		list.add("Brian");
		list.add("Charlie");
		list.add("Charlie");
		list.add("Brian");
		list.add("Abe");
		System.out.println("Names list: "+list);
		
		// 1. numUniqueNamesFlawed() returns the wrong number of unique names in list.
		// identify the flaw.
		int n = numUniqueNamesFlawed(list);
		System.out.println("Wrong number of unique names: " + n);
		
		
		// 2. write and use uniqueNames() to get a list of unique names in list, 
		// and assign it to uList. print uList
		ArrayList<String> uList = uniqueNames(list);
		System.out.println("unique names: "+uList);
		n = numUniqueNames(list);
		System.out.println("correct number of unique names: "+ n );

	}
	
	/**
	 * @param list
	 * @return : a new arrayList containing the unique names in list
	 */
	public static ArrayList<String> uniqueNames(ArrayList<String> list) {

		return null;
	}

	/**
	 * @param list
	 * @return : number of unique names in list.
	 */
	public static int numUniqueNames(ArrayList<String> list) {

		return 0;
	}

	public static int numUniqueNamesFlawed(ArrayList<String> list) {
		if (list.size() == 0) {
			return 0;
		}
		String prevName = list.get(0);
		String currName = "";
		int numNames = 1;
		for (int i=1; i<list.size(); i++){
			currName = list.get(i);
			if ( !prevName.equals(currName) ){
				numNames++;
				prevName = currName;
			}
		}
		return numNames;
	}



}
