package chapter05;

public class _12_IndexOutOfBounds {

	public static void main(String[] args) {
		
		String str = "abcde";
		
		//abcde : length = 5 (n)
		//01234 : goes up to n-1
		
		// the following will generate a run time error.
		// why does this happen?
		for(int i = 0; i <= str.length(); i++) {
			System.out.println(str.substring(i, i + 1));
		}
		
		// valid index : [0, n-1], where n is the length of the string.
		// note: .substring(a, n) is permissible, since n is not actually accessed.
		
	}


}
