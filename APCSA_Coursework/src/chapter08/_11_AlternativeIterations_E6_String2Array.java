package chapter08;

public class _11_AlternativeIterations_E6_String2Array {


	public static void main(String[] args) {
		
		String from = "slartibartfast";
		String[] to1 = new String[10];
		String[] to2 = new String[20];

		// 1. finish the method string2Array()
		// 2. use string2Array() to fit from  to to1 and to2
		string2Array(from, to1);
		printArray(to1);
		string2Array(from, to2);
		printArray(to2);
		
	}

	/** 
	 * fits all elements in from into to.
	 * if 'from' is longer than 'to', fill in as much as 'to' can take
	 * if 'from' is shorter than 'to', leave the remaining integers as null
	 * @param from
	 * @param to
	 */
	public static void string2Array(String from, String[] to) {

	}
	
	// from ch.06
	public static void printArray(String[] arr) {
		System.out.println(" i : a[i]");
		System.out.println("=========");
		for(int i=0; i<arr.length; i++) {
			System.out.println( " "+i+ " : " + arr[i]);
		}
		System.out.println();
	}
	

}
