/*
 * $Id:$
 */

package part2.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static part2.stack.ArithmeticStack.*;
import static part2.stack.ArithmeticStack.Operation.*;

/**
 * Polish notation calculator
 * @author Mikhail Glukhikh
 */
@SuppressWarnings("WeakerAccess")
public class PolishNotationCalculator {

    static private Map<String, Operation> operationMap = new HashMap<>();
    static {
        operationMap.put("+", PLUS);
        operationMap.put("-", MINUS);
        operationMap.put("*", TIMES);
        operationMap.put("/", DIV);
        operationMap.put("^", POWER);
    }

    static public double calc(String expr) {
        final ArithmeticStack stack = new ArithmeticStack();
        for (String arg: expr.split(" ")) {
            Operation op = operationMap.get(arg);
            if (op == null) {
                double x = Double.parseDouble(arg);
                stack.push(x);
            }
            else {
                stack.execute(op);
            }
        }
        return stack.top();
    }

    static public double calcFunctional(String expr) {
        final ArithmeticStack stack = new ArithmeticStack();
        Arrays.asList(expr.split(" ")).forEach(s -> {
            Operation op = operationMap.get(s);
            if (op == null) {
                double x = Double.parseDouble(s);
                stack.push(x);
            }
            else {
                stack.execute(op);
            }
        });
        return stack.top();
    }
}
