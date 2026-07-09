package chapter05;

public class _04_StringComparison {

	public static void main(String[] args) {
		
		// care is needed when comparing strings
		
		// case 1
		if("AA" == "AA")
		{
			System.out.println("true");
		}else {
			System.out.println("false");
		}

		
		// case 2
		String a1 = "AA";
		String a2 = "AA";
		System.out.println(a1);
		System.out.println(a2);
		if(a1 == a2)
		{
			System.out.println("true");
		}else {
			System.out.println("false");
		}
		
		// case 3
		String s1 = new String("AA");
		String s2 = new String("AA");
		System.out.println(s1);
		System.out.println(s2);
		if(s1 == s2)
		{
			System.out.println("true");
		}else {
			System.out.println("false");
		}



		// then how do you compare strings?
		if(s1.equals(s2))
		{
			System.out.println("true");
		}else {
			System.out.println("false");
		}
		
		
		// Bottom line: 
		// When comparing strings, use .equals()
		// String1.equals(String2)
		
	}

}
