/*
 * $Id:$
 */

package part2.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * Stack class
 * @author Mike
 */
@SuppressWarnings("WeakerAccess")
public class Stack<E> {
    /**
     * Stack content
     */
    private final Deque<E> stack = new ArrayDeque<>();

    /**
     * Push into stack
     * @param elem pushed element
     */
    public void push(E elem) {
        stack.push(elem);
    }

    /**
     * Pop from stack
     * @return popped element
     * @throws NoSuchElementException if stack is empty
     */
    public E pop() throws NoSuchElementException {
        return stack.pop();
    }

    /**
     * Get stack top without popping it
     * @return stack top
     * @throws NoSuchElementException if stack is empty
     */
    public E top() throws NoSuchElementException {
        return stack.getFirst();
    }
}
