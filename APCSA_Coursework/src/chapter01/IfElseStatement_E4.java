package chapter01;

public class IfElseStatement_E4 
{
	public static void main(String[] args) {

		// variable declarations
		int a, b, c;

		a = 1;
		b = 2;
		c = 1;
		// test for different value combinations: 
		// 1 2 -3 : D>0
		// 1 2 3  : D<0
		// 1 2 1  : D=0
		
		/** a, b, c are coefficients for the quadratic equation
		 * 	ax^2 + bx + c = 0
		 *  write a routine that prints the type of the roots of this equation;
		 *  Discriminant: D = b^2-4ac
		 *  D>0: "Two real roots"
		 *  D=0: "Double root"
		 *  D<0: "Complex conjugate roots"
		 */
		
		double d = Math.pow(b, 2) - 4 * a * c;
		if(d > 0)
		{
			System.out.println("Two real roots");
		} else if(d < 0) {
			System.out.println("Complex conjugate roots");
		} else { // d == 0
			System.out.println("Double roots...");
		}
	}
}
