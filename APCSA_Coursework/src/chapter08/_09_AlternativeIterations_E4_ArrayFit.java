package chapter08;

public class _09_AlternativeIterations_E4_ArrayFit {


	public static void main(String[] args) {
		
		int[] from = { 1, 2, 3, 4, 5, 6 };
		int[] to1 = new int[8];
		int[] to2 = new int[4];

		// 1. finish the method fitArray()
		// 2. use fitArray() to fit from  to to1 and to2
		fitArray(from, to1);
		printArray(to1);
		fitArray(from, to2);
		printArray(to2);
		
	}

	/** 
	 * fits all elements in from into to.
	 * if 'from' is longer than 'to', fill in as much as 'to' can take
	 * if 'from' is shorter than 'to', leave the remaining integers as 0
	 * @param from
	 * @param to
	 */
	public static void fitArray(int[] from, int[] to) {

	}
	
	// from ch.06
	public static void printArray(int[] arr) {
		System.out.println(" i : a[i]");
		System.out.println("=========");
		for(int i=0; i<arr.length; i++) {
			System.out.println( " "+i+ " : " + arr[i]);
		}
		System.out.println();
	}
	

}
