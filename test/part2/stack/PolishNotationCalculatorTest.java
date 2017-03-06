/*
 * $Id:$
 */

package part2.stack;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mikhail Glukhikh
 */
public class PolishNotationCalculatorTest {

    @Test
    public void test1() {
        assertEquals(3.0, PolishNotationCalculator.calc("1 2 +"), 1e-10);
    }

    @Test
    public void test2() {
        assertEquals(10.0, PolishNotationCalculator.calc("6 -4 -"), 1e-10);
    }

    @Test
    public void test3() {
        assertEquals(3.0, PolishNotationCalculator.calc("10 1 2 3 * + -"), 1e-10);
    }

    @Test(expected=IllegalArgumentException.class)
    public void test4() {
        PolishNotationCalculator.calc("1 -");
    }

    @Test
    public void test5() {
        assertEquals(8.0, PolishNotationCalculator.calc("2 3 ^"), 1e-10);
    }

    @Test
    public void test6() {
        assertEquals(-1.0, PolishNotationCalculator.calc("1 n"), 1e-10);
    }
}
