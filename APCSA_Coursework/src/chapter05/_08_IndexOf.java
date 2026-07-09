package chapter05;

public class _08_IndexOf {

	public static void main(String[] args) {
		
		String str = "abcdeabcde";
		
		// this method can be used to find the index of multiple character strings!!
		// actually, there is an indexOf method ;)
		
		String b = "b";
		String cde = "cde";
		String absent = "not";
		
		// prints 1, 2, -1
		System.out.println(str.indexOf(b));
		System.out.println(str.indexOf(cde));
		System.out.println(str.indexOf(absent));

		// finding the second a
		int first = str.indexOf("a");
		int second = str.indexOf("a", first + 1);
		int third = str.indexOf("a", second + 1);
		System.out.println(first);
		System.out.println(second);

	}

}
