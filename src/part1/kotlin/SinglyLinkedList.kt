package part1.kotlin

class SinglyLinkedList {

    private var start: Node? = null

    private class Node(
            val value: Int,
            var next: Node? // null means the node is the last in the list
    )

    fun add(newValue: Int) {
        // Node is added to the first position
        start = Node(newValue, start)
    }

    // Returns false for empty list
    fun removeFirst(): Boolean {
        if (start == null) return false
        start = start?.next
        return true
    }

    // Returns false for empty list
    fun removeLast(): Boolean {
        val start = start ?: return false
        if (start.next == null) {
            this.start = null
        } else {
            var current = start
            while (current.next?.next != null) {
                current = current.next!!
            }
            current.next = null
        }
        return true
    }

    fun remove(removedValue: Int): Boolean {
        val start = start ?: return false
        if (start.value == removedValue) {
            this.start = start.next
            return true
        } else {
            var current = start
            while (current.next != null) {
                if (current.next?.value == removedValue) {
                    current.next = current.next?.next
                    return true
                }
                current = current.next!!
            }
            return false
        }
    }

    fun size(): Int {
        var current = start
        var result = 0
        while (current != null) {
            current = current.next
            result++
        }
        return result
    }

    override fun toString(): String {
        return buildString {
            append("[")
            val start = start
            if (start != null) {
                append(start.value)
                var current = start
                while (current?.next != null) {
                    current = current.next
                    append(", ")
                    append(current!!.value)
                }
            }
            append("]")
        }
    }
}
