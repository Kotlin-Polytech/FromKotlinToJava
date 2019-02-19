package part1.kotlin

import org.junit.Assert.*
import org.junit.Test

class SinglyLinkedListTest {

    @Test
    fun add() {
        val list = SinglyLinkedList()
        list.add(0)
        assertSame(1, list.size())
    }

    @Test
    fun removeFirst() {
        val list = SinglyLinkedList()
        list.add(0)
        assertTrue(list.removeFirst())
        assertSame(0, list.size())
        assertFalse(list.removeFirst())
    }

    @Test
    fun removeLast() {
        val list = SinglyLinkedList()
        list.add(4)
        list.add(3)
        list.add(2)
        list.add(1)
        list.add(0)
        assertSame(5, list.size())
        assertTrue(list.removeLast())
        assertSame(4, list.size())
        assertEquals("[0, 1, 2, 3]", list.toString())
        assertTrue(list.removeLast())
        assertSame(3, list.size())
        assertEquals("[0, 1, 2]", list.toString())
        assertFalse(list.remove(3))
        assertFalse(list.remove(4))
        assertTrue(list.remove(2))
    }

    @Test
    fun remove() {
        val list = SinglyLinkedList()
        for (i in 0..19) {
            list.add(i)
            assertSame(i + 1, list.size())
        }
        list.remove(7)
        list.remove(13)
        var size = 18
        assertSame(size, list.size())
        for (i in 0..19) {
            if (i == 7 || i == 13) {
                assertFalse(list.remove(i))
            } else {
                assertTrue(list.remove(i))
                size--
                assertSame(size, list.size())
            }
        }
        assertSame(0, size)
        assertSame(0, list.size())
    }

    @Test
    fun size() {
        val list = SinglyLinkedList()
        list.add(5)
        list.add(7)
        list.add(10)
        assertSame(3, list.size())
        list.remove(7)
        assertSame(2, list.size())
    }
}