package part1;

@SuppressWarnings("WeakerAccess")
public final class SinglyLinkedList /*implements List<Integer>*/ {

    private static class Node {
        int value;
        Node next; // null means the node is the last in the list

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node start = null;

    public void add(int newValue) {
        // Node is added to the first position
        start = new Node(newValue, start);
    }

    // Returns false for empty list
    public boolean removeFirst() {
        if (start == null) return false;
        start = start.next;
        return true;
    }

    // Returns false for empty list
    public boolean removeLast() {
        if (start == null) return false;
        if (start.next == null) {
            start = null;
        } else {
            Node current = start;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        return true;
    }

    public boolean remove(int removedValue) {
        if (start == null) return false;
        if (start.value == removedValue) {
            start = start.next;
            return true;
        } else {
            Node current = start;
            while (current.next != null) {
                if (current.next.value == removedValue) {
                    current.next = current.next.next;
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }

    public int size() {
        Node current = start;
        int result = 0;
        while (current != null) {
            current = current.next;
            result++;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (start != null) {
            sb.append(start.value);
            Node current = start;
            while (current.next != null) {
                current = current.next;
                sb.append(", ");
                sb.append(current.value);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
