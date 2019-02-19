package part1;

import org.junit.Test;

import static org.junit.Assert.*;

public class SinglyLinkedListTest {

    @Test
    public void add() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.add(0);
        assertSame(1, list.size());
    }

    @Test
    public void removeFirst() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.add(0);
        assertTrue(list.removeFirst());
        assertSame(0, list.size());
        assertFalse(list.removeFirst());
    }

    @Test
    public void removeLast() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(0);
        assertSame(5, list.size());
        assertTrue(list.removeLast());
        assertSame(4, list.size());
        assertEquals("[0, 1, 2, 3]", list.toString());
        assertTrue(list.removeLast());
        assertSame(3, list.size());
        assertEquals("[0, 1, 2]", list.toString());
        assertFalse(list.remove(3));
        assertFalse(list.remove(4));
        assertTrue(list.remove(2));
    }

    @Test
    public void remove() {
        SinglyLinkedList list = new SinglyLinkedList();
        for (int i = 0; i < 20; i++) {
            list.add(i);
            assertSame(i + 1, list.size());
        }
        list.remove(7);
        list.remove(13);
        int size = 18;
        assertSame(size, list.size());
        for (int i = 0; i < 20; i++) {
            if (i == 7 || i == 13) {
                assertFalse(list.remove(i));
            } else {
                assertTrue(list.remove(i));
                size--;
                assertSame(size, list.size());
            }
        }
        assertSame(0, size);
        assertSame(0, list.size());
    }

    @Test
    public void size() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.add(5);
        list.add(7);
        list.add(10);
        assertSame(3, list.size());
        list.remove(7);
        assertSame(2, list.size());
    }
}