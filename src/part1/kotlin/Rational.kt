package part1.kotlin

import java.lang.Math.abs

class Rational(numerator: Int, denominator: Int) {
    private val numerator: Int

    private val denominator: Int

    private tailrec fun gcd(a: Int, b: Int): Int =
            when {
                a == b || b == 0 -> a
                a == 0 -> b
                a > b -> gcd(a % b, b)
                else -> gcd(a, b % a)
            }

    init {
        if (denominator == 0) throw ArithmeticException("Denominator cannot be zero")
        var gcd = gcd(abs(numerator), abs(denominator))
        if (denominator < 0) gcd = -gcd
        this.numerator = numerator / gcd
        this.denominator = denominator / gcd
    }

    operator fun plus(other: Rational) = Rational(
            numerator * other.denominator + denominator * other.numerator,
            denominator * other.denominator
    )

    operator fun unaryMinus() = Rational(-numerator, denominator)

    operator fun minus(other: Rational) = plus(other.unaryMinus())

    operator fun times(other: Rational) = Rational(numerator * other.numerator, denominator * other.denominator)

    operator fun div(other: Rational) = Rational(numerator * other.denominator, denominator * other.numerator)

    fun toInt() = numerator / denominator

    fun toDouble() = numerator.toDouble() / denominator

    override fun equals(other: Any?) =
            when {
                this === other -> true
                other is Rational -> numerator == other.numerator && denominator == other.denominator
                else -> false
            }

    override fun hashCode(): Int {
        var result = numerator
        result = 31 * result + denominator
        return result
    }

    override fun toString() = "$numerator/$denominator"
}