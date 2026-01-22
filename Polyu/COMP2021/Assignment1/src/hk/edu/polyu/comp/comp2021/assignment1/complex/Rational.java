package hk.edu.polyu.comp.comp2021.assignment1.complex;

public class Rational {

    // Task 2: add the missing fields
    public int numerator;
    public int denominator;


    // Task 3: 	Complete the constructor and
    // the methods add, subtract, multiply, divide, simplify, and toString.

    public Rational(int numerator, int denominator){
        // Todo: complete the constructor
        if (denominator == 0)
        {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();

    }

    public Rational add(Rational other){
        // Todo: complete the method
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    public Rational subtract(Rational other){
        // Todo: complete the method
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Rational(newNumerator, newDenominator);

    }

    public Rational multiply(Rational other){
        // Todo: complete the method
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    public Rational divide(Rational other){
        // Todo: complete the method
        if(other.numerator == 0)
        {
            throw new IllegalArgumentException("Cannot be divided by zero");
        }
        int newNumerator = this.numerator * other.denominator;
        int newDenominator = this.denominator * other.numerator;
        return new Rational(newNumerator, newDenominator);

    }

    public String toString(){
        // Todo: complete the method
        if(denominator == 1)
        {
            return String.valueOf(numerator);
        }
        return numerator + "/" + denominator;

    }

    public void simplify(){
        // Todo: complete the method
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator = numerator / gcd;
        denominator = denominator / gcd;
        if(denominator < 0)
        {
            numerator = -numerator;
            denominator = -denominator;
        }


    }

    // ========================================== Do not change the methods below.

    private int getNumerator() {
        return numerator;
    }

    private void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    private int getDenominator() {
        return denominator;
    }

    private void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    private int gcd(int a, int b){
        if(b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
