package chapter05;

public class _14_CompareTo {

	public static void main(String[] args) {
		
		// every character has an innate numerical value (ASCII code)
		char ch = 'a';
		System.out.println( (int) ch ); // a == > 97
		System.out.println( (char) 97 ); // 97 == > a
		
		// .compareTo() is a string method that returns an integer value
		// this value represents the relative value(order) of two strings
		// A.compareTo(B) ==> A - B ( in terms of ASCII )
		
		String a = "a";
		String b = "b";
		System.out.println( "a-b : " + a.compareTo(b) ); // 97 - 98 = -1
		System.out.println( "b-a : " + b.compareTo(a) ); // 98 - 97 = 1
		System.out.println( "a-a : " + a.compareTo(a) ); // 98 - 98 = 0
		
		// if first characters are the same, compareTo looks at the next pair
		String abb = "abb";
		String abc = "abc";
		System.out.println( abb.compareTo(abc) ); // b - c
		
		// can also be used instead of .equals()
		String s1 = new String("AA");
		String s2 = new String("AA");
		if(s1.compareTo(s2) == 0 )
		{
			System.out.println("true");
		}else {
			System.out.println("false");
		}
		
		
	}


}
