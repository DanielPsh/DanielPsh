package chapter07;

import java.util.ArrayList;

public class _02_WrapperClasses {

	public static void main(String[] args) {

		// the Type for ArrayLists have to be Objects
		// ArrayList< *type* > list = new ArrayList< *type* >();
		
		// CORRECT:
		ArrayList<Integer> intList = new ArrayList<Integer>();
		ArrayList<Double> doubleList = new ArrayList<Double>();
		ArrayList<Character> charList = new ArrayList<Character>();
		ArrayList<Boolean> booleanList = new ArrayList<Boolean>();	
		
		// INCORRECT:
		/*
		ArrayList<int> intList = new ArrayList<int>();
		ArrayList<double> doubleList = new ArrayList<double>();
		ArrayList<char> charList = new ArrayList<char>();
		ArrayList<boolean> booleanList = new ArrayList<boolean>();	
		*/
		
		// Integer is a special class that 'auto-wrap's a number into an object
		for(int i = 0; i < 10; i++)
		{
			intList.add(i);
		}
		System.out.println(intList);
		
		// removes everything in intList
		intList.removeAll(intList);
		System.out.println(intList);
		
		
		// the proper way to add Integer objects...
		for(int i = 0; i < 10; i++)
		{
			intList.add( new Integer(i) );
		}
		System.out.println(intList);
		// but the above is unnecessary ... 
		
	}

}
