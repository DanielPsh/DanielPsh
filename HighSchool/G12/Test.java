
import java.util.Scanner;
public class Test {
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        /* 
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a num: ");
        String choice = input.next();

        checkInput(choice);
        */
        Scanner scanner = new Scanner(System.in);
        int intValue = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.print("Enter input: ");
            String input = scanner.next();

            try {
                intValue = Integer.parseInt(input);
                isValidInput = true; // Set flag to true to exit loop
            } catch (NumberFormatException e) {
                System.out.println("Input should be an integer, not a string. Please try again.");
            }
        }

        System.out.println("Input is an integer: " + intValue);
    }
    public static void checkInput(String input) {
        // Check if the input can be parsed as an integer
        try {
            int intValue = Integer.parseInt(input);
            System.out.println("Input is an integer.");
        } catch (NumberFormatException e) {
            // If parsing as integer fails, it's a string
            System.out.println("Input should be an integer, not a string.");
        }
    }

}
