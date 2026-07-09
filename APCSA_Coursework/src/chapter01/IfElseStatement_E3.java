package chapter01;

public class IfElseStatement_E3 
{
	public static void main(String[] args) {

		// variable declarations
		int score;
		
		score = 95;	// <== change this and test your code
		
		/** study the following routine that
		 * prints grade for score ranges:
		 * A: scores equal to or greater than 90
		 * B: scores equal to or greater than 80
		 * C: scores equal to or greater than 70
		 * D: scores equal to or greater than 60
		 * F: scores below 60
		 * The code does not seem to be functioning correctly. 
		 * Rewrite the code so that it behaves as intended!
		 */
		
		String grade = "";
		
		if ( score >= 90 ) {
			grade = "A";
		}
		else if ( score >= 80 ) {
			grade = "B";
		}
		else if ( score >= 70 ) {
			grade = "C";
		}
		else if ( score >= 60 ) {
			grade = "D";
		} else {
			grade = "F";
		}
		System.out.println(score + ": " + grade);
		
	}
}
