package part1;

import static java.lang.Math.abs;

@SuppressWarnings("WeakerAccess")
public final class Rational {
    private final int numerator;

    private final int denominator;

    static private int gcd(int a, int b) {
        if (a == b || b == 0) return a;
        else if (a == 0) return b;
        else if (a > b) return gcd(a % b, b);
        else return gcd(a, b % a);
    }

    public Rational(int numerator, int denominator) {
        if (denominator == 0) throw new ArithmeticException("Denominator cannot be zero");
        int gcd = gcd(abs(numerator), abs(denominator));
        if (denominator < 0) gcd = -gcd;
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Rational plus(Rational other) {
        return new Rational(
                numerator * other.denominator + denominator * other.numerator,
                denominator * other.denominator
        );
    }

    public Rational unaryMinus() {
        return new Rational(-numerator, denominator);
    }

    public Rational minus(Rational other) {
        return plus(other.unaryMinus());
    }

    public Rational times(Rational other) {
        return new Rational(numerator * other.numerator, denominator * other.denominator);
    }

    public Rational div(Rational other) {
        return new Rational(numerator * other.denominator, denominator * other.numerator);
    }

    public int toInt() {
        return numerator / denominator;
    }

    public double toDouble() {
        return ((double) numerator ) / denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Rational) {
            Rational other = (Rational) obj;
            return numerator == other.numerator && denominator == other.denominator;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    @Override
    public String toString() {
        return "" + numerator + "/" + denominator;
    }
}
