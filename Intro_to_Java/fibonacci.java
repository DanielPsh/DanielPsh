package Intro_to_Java;

public class fibonacci {
    public static void main(String[] args) {
        int n = 10;
        int result = fibonacci(n);
        System.out.println(result);
    }

    public static int fibonacci(int n) {
        if (n <= 1)
            return (n);
        return (fibonacci(n - 1) + fibonacci(n - 2));
    }
}
