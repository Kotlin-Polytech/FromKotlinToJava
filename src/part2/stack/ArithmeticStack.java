/*
 * $Id:$
 */

package part2.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.BiFunction;

/**
 * Arithmetic stack class
 * @author Mikhail Glukhikh
 */
@SuppressWarnings("WeakerAccess")
public class ArithmeticStack {

    private final Deque<Double> stack = new LinkedList<>();

    public enum Operation {
        PLUS,
        MINUS,
        TIMES,
        DIV,
        POWER;

        public BiFunction<Double, Double, Double> getFunction() {
            switch (this) {
                case PLUS: return (x, y) -> x + y;
                case MINUS: return (x, y) -> y - x;
                case TIMES: return (x, y) -> x * y;
                case DIV: return (x, y) -> y / x;
                case POWER: return (x, y) -> Math.pow(y, x);
                default: throw new AssertionError("Non-exhaustive when");
            }
        }
    }

    public void push(double x) {
        stack.push(x);
    }

    public double top() {
        return stack.peek();
    }

    public void execute(Operation op) {
        stack.push(op.getFunction().apply(stack.pop(), stack.pop()));
    }
}