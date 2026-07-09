package chapter05;

public class _01_StringLength {

	public static void main(String[] args) {
		
		int a = 1; // type : primitive!!!
		String str = "HowLongIsThisString"; // type : object!! Scanner.. new..
		
		// find the length of the string str
		int len = str.length();
		System.out.println(str + " => " + len + " characters long.");
		
		
		/// find the character with index k: .substring(k, k+1)
		int k = 2;
		String kth = str.substring(k, k + 1); 
		System.out.println(kth);
		
		System.out.println(str.charAt(k)); // charAt returns a character
		char c = '2'; // primitive
		
		
	}

}
