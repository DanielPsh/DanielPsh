package chapter06;

public class _09_SwapAlgorithm {

	public static void main(String[] args) {

		int a = 1;
		int b = 2;
		
		System.out.println("Before swap:");
		System.out.println("a = "+a);
		System.out.println("b = "+b);
		
		// let's try a swap.
		int temp = a;
		a = b;
		b = temp;
		
		
		
		System.out.println("After swap:");
		System.out.println("a = "+a);
		System.out.println("b = "+b);

	}

}
