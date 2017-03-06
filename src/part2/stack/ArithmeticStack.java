/*
 * $Id:$
 */

package part2.stack;

import java.util.NoSuchElementException;

/**
 * Arithmetic stack class
 * @author Mikhail Glukhikh
 */
@SuppressWarnings("WeakerAccess")
public class ArithmeticStack extends Stack<Double> {

    /**
     * Auxiliary class
     */
    public enum Operation {
        ADD,
        SUB,
        MUL,
        POWER,
        // Unary minus
        NEG,
        DIV
    }

    /**
     * Execute given operation on stack
     * @param op operation
     * @throws NoSuchElementException if stack does not have enough numbers inside
     */
    public void execute(Operation op) throws NoSuchElementException {
        double x = pop();
        double y = op != Operation.NEG ? pop() : 0.0;
        switch (op) {
            case ADD:
                push(x + y);
                break;
            case SUB:
                push(y - x);
                break;
            case MUL:
                push(x * y);
                break;
            case DIV:
                push(y / x);
                break;
            case POWER:
                push(Math.pow(y, x));
                break;
            case NEG:
                push(-x);
                break;
        }
    }
}
