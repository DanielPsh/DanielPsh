package chapter05;

public class _10_Substrings {

	public static void main(String[] args) {
		
		String str = "abcdefghij";
		
		// substring() can be used to extract more than a single character
		// substring( k, k+m ) : m characters from k
		// NOTE: substring(a, b) contains the char at index a, but not the char at index b
		// also: substring(k) : everything from k to the end
		
		// abcdefghij
		// 0123456789
		// de (3,5)
		System.out.println(str.substring(3, 3 + 2));

		// fghij
		System.out.println(str.substring(5, 5 + 5));
		System.out.println(str.substring(5)); // 나머지 5부터 끝까
		
	}

}
