package chapter07;

import java.util.ArrayList;

public class _01_ArrayLists {

	public static void main(String[] args) {

		// you don't need to know the length before declaration
		// ArrayList< *type* > list = new ArrayList< *type* >();
		ArrayList<String> list = new ArrayList<String>();
		System.out.println(list);
		
		// lists start empty, append elements by .add()
		// .add(object) appends elements to the end of the list
		// .add(index, object) appends elements at index
		list.add("Luke");
		System.out.println(list);
		list.add("Leia");
		System.out.println(list);
		list.add(0, "Anakin");
		System.out.println(list);
		
		// length of lists: .size()
		System.out.println("There were " + list.size() + " Skywalkers.");
		
		
		// Static Traversal
		// retrieve elements with: .get()
		for(int i = 0; i < list.size(); i++)
		{
			System.out.println( list.get(i) + " Skywalker");
		}
		
		
		// look for elements with .indexOf()
		System.out.println("The Emperor had been searching for Anakin...");
		int indexOfAnakin = list.indexOf("Anakin");
		System.out.println("[warning!] Anakin found at index: " + indexOfAnakin);
		
		// change elements with .set();
		// Anakin => Darth Vader
		System.out.println("And finally turned him to the dark side..");
		list.set(indexOfAnakin, "Darth Vader");
		System.out.println("Revised family tree:" + list);

		// discard elements with .remove();
		System.out.println("So Anakin is no longer a Skywalker.");
		String sith = list.remove(indexOfAnakin);

		// in a galaxy far far away...
		System.out.println("And has joined the siths.");
		ArrayList<String> siths = new ArrayList<String>();
		siths.add("Emperor Palpatine");
		siths.add(sith);
		System.out.println("So now the Skywalkers:" + list);
		System.out.println("must save the galaxy from: " + siths);
		
	}

}
