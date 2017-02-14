package part1.kotlin

import junit.framework.TestCase.assertEquals
import org.junit.Test

class RationalTest {
    @Test
    fun plus() {
        assertEquals(Rational(1, 2), Rational(1, 6) + Rational(1, 3))
    }

    @Test
    operator fun unaryMinus() {
        assertEquals(Rational(1, 2), -Rational(3, -6))
    }

    @Test
    fun minus() {
        assertEquals(Rational(1, 3), Rational(1, 2) - Rational(1, 6))
    }

    @Test
    fun times() {
        assertEquals(Rational(3, 10), Rational(3, 4) * Rational(2, 5))
    }

    @Test
    fun div() {
        assertEquals(Rational(2, 5), Rational(3, 10) / Rational(3, 4))
    }

    @Test
    fun toInt() {
        assertEquals(1, Rational(3, 2).toInt().toLong())
    }

    @Test
    fun toDouble() {
        assertEquals(0.5, Rational(3, 6).toDouble(), 1e-10)
    }

    @Test
    fun equals() {
        assertEquals(Rational(1, 2), Rational(2, 4))
        assertEquals(Rational(-1, 2), Rational(2, -4))
        assertEquals(Rational(2, 3), Rational(-4, -6))
    }

    @Test(expected = ArithmeticException::class)
    fun divZero() {
        Rational(1, 0)
    }

}