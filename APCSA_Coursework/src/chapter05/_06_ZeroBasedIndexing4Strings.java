package chapter05;

public class _06_ZeroBasedIndexing4Strings {

	public static void main(String[] args) {
		
		String str = "abcde";
		
		System.out.println(str);
		
		// print out the index numbers of the above string
		for(int i = 0; i < str.length(); i++) {
			System.out.println(str.subSequence(i, i + 1) + " : " + i);
		}
		
	}

}
