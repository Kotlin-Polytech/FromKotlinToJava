package part4.producerconsumer;

import java.util.ArrayDeque;
import java.util.Deque;

@SuppressWarnings("WeakerAccess")
public class StringQueue {

    private final Deque<String> strings;

    private final int limit;

    public StringQueue(final int limit) {
        strings = new ArrayDeque<>(limit);
        this.limit = limit;
    }

    public synchronized void putString(String string) throws InterruptedException {
        while (strings.size() == limit) {
            System.out.println("WAITING for a place");
            wait();
        }
        strings.addLast(string);
        notifyAll();
    }

    public synchronized String getString() throws InterruptedException {
        while (strings.isEmpty()) {
            System.out.println("WAITING for a string");
            wait();
        }
        String result = strings.removeFirst();
        notifyAll();
        return result;
    }
}
