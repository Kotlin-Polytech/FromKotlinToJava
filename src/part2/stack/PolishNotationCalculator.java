/*
 * $Id:$
 */

package part2.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static part2.stack.ArithmeticStack.*;

/**
 * PolishNotationCalculator notation calculator
 * @author Mikhail Glukhikh
 */
@SuppressWarnings("WeakerAccess")
public class PolishNotationCalculator {

    /**
     * Map string representation to operation
     */
    static Map<String, Operation> operationMap = new HashMap<>();
    static {
        // Static initialization of the map
        operationMap.put("+", Operation.ADD);
        operationMap.put("-", Operation.SUB);
        operationMap.put("*", Operation.MUL);
        operationMap.put("/", Operation.DIV);
        operationMap.put("^", Operation.POWER);
        operationMap.put("n", Operation.NEG);
    }

    /**
     * <p>Calculate given reverse polish notation</p>
     *
     * <p>Notation elements must be separated by exactly one space.
     * Elements may be real numbers and operations.</p>
     *
     * @param expr reverse polish notation string
     * @return reverse polish notation value
     * @throws IllegalArgumentException if expression has unknown elements or uses incorrect operations order
     */
    static public double calc(String expr) throws IllegalArgumentException {
        final ArithmeticStack stack = new ArithmeticStack();
        final String[] args = expr.split(" ");
        for (String arg: args) {
            Operation op = operationMap.get(arg);
            if (op==null) {
                try {
                    double x = Double.parseDouble(arg);
                    stack.push(x);
                }
                catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            "Cannot parse polish notation element: " + arg);
                }
            }
            else {
                try {
                    stack.execute(op);
                } catch (NoSuchElementException ex) {
                    throw new IllegalArgumentException(
                            "Cannot calculate polish notation: " + expr +
                                    " error while executing operation " + arg, ex);
                }
            }
        }
        return stack.top();
    }
}
