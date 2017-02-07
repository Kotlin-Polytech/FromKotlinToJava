package part1;

import org.junit.Test;

import static org.junit.Assert.*;

public class RationalTest {
    @Test
    public void plus() throws Exception {
        assertEquals(new Rational(1, 2),
                new Rational(1, 6).plus(new Rational(1, 3)));
    }

    @Test
    public void unaryMinus() throws Exception {
        assertEquals(new Rational(1, 2), new Rational(3, -6).unaryMinus());
    }

    @Test
    public void minus() throws Exception {
        assertEquals(new Rational(1, 3),
                new Rational(1, 2).minus(new Rational(1, 6)));
    }

    @Test
    public void times() throws Exception {
        assertEquals(new Rational(3, 10),
                new Rational(3, 4).times(new Rational(2, 5)));
    }

    @Test
    public void div() throws Exception {
        assertEquals(new Rational(2, 5),
                new Rational(3, 10).div(new Rational(3, 4)));
    }

    @Test
    public void toInt() throws Exception {
        assertEquals(1, new Rational(3, 2).toInt());
    }

    @Test
    public void toDouble() throws Exception {
        assertEquals(0.5, new Rational(3, 6).toDouble(), 1e-10);
    }

    @Test
    public void equals() throws Exception {
        assertEquals(new Rational(1, 2), new Rational(2, 4));
        assertEquals(new Rational(-1, 2), new Rational(2, -4));
        assertEquals(new Rational(2, 3), new Rational(-4, -6));
    }

    @Test(expected = ArithmeticException.class)
    public void divZero() {
        new Rational(1, 0);
    }

}