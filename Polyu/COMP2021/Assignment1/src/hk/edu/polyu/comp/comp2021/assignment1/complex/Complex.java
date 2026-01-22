package hk.edu.polyu.comp.comp2021.assignment1.complex;

public class Complex {

    // Task 4: add the missing fields
    private Rational real;
    private Rational imag;


    // Task 5: Complete the constructor as well as the methods add, subtract, multiply, divide, and toString.
    public Complex(Rational real, Rational imag) {
        // Todo: complete the constructor
        this.real = real;
        this.imag = imag;
        simplify();
    }


    public Complex add(Complex other) {
        // Todo: complete the method
        Rational newReal = this.real.add(other.real);
        Rational newImag = this.imag.add(other.imag);
        return new Complex(newReal, newImag);

    }

    public Complex subtract(Complex other) {
        // Todo: complete the method
        Rational newReal = this.real.subtract(other.real);
        Rational newImag = this.imag.subtract(other.imag);
        return new Complex(newReal, newImag);

    }

    public Complex multiply(Complex other) {
        // Todo: complete the method
        Rational newReal = this.real.multiply(other.real).subtract(this.imag.multiply(other.imag));
        Rational newImag = this.real.multiply(other.imag).add(this.imag.multiply(other.real));
        return new Complex(newReal, newImag);

    }

    public Complex divide(Complex other) {
        // Todo: complete the method
        // you may assume 'other' is never equal to '0+/-0i'.
        Rational denominator = other.real.multiply(other.real).add(other.imag.multiply(other.imag));
        Rational newReal = this.real.multiply(other.real).add(this.imag.multiply(other.imag)).divide(denominator);
        Rational newImag = this.imag.multiply(other.real).subtract(this.real.multiply(other.imag)).divide(denominator);
        return new Complex(newReal, newImag);


    }

    public void simplify() {
        // Todo: complete the method
        real.simplify();
        imag.simplify();

    }

    public String toString() {
        // Todo: complete the method
        Rational zero = new Rational(0, 1);
        String result = real.toString();

        if (!imag.equals(zero))
        {
            String imagStr = imag.toString();
            if (imagStr.startsWith("-"))
            {
                result += imagStr + "i";
            } else
            {
                result += "+" + imagStr + "i";
            }
        }

        return result;

    }

    // =========================== Do not change the methods below


    private Rational getReal() {
        return real;
    }

    private void setReal(Rational real) {
        this.real = real;
    }

    private Rational getImag() {
        return imag;
    }

    private void setImag(Rational imag) {
        this.imag = imag;
    }
}
