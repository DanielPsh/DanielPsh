package hk.edu.polyu.comp.comp2021.assignment1;

public class SpecialNumber {

	public static boolean isSpecial(int num) {
		// Task 6: Complete the method so that it returns true if and only if 'num' is special
        int original = num;
        int sum = 0;
        int digits = String.valueOf(num).length();

        if (num < 0)
            return false;

        while (num > 0) {
            int digit = num % 10;
            sum += (int) + Math.pow(digit, digits);
            num /= 10;
        }

        return sum == original;
	}


}
