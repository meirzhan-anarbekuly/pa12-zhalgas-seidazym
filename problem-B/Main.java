import java.util.EmptyStackException;
import java.util.Iterator;

class LinkedStack<E> implements Iterable<E> {
    private Node<E> top;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void push(E e) {
        top = new Node<>(e, top);
        size++;
    }

    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E element = top.element;
        top = top.next;
        size--;
        return element;
    }

    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.element;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<E> current = top;
        while (current != null) {
            sb.append(current.element).append(" ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<E> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<E> {
        private Node<E> current = top;

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            E element = current.element;
            current = current.next;
            return element;
        }
    }

    public static void main(String[] args) {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        linkedStack.push(4);
        linkedStack.push(5);
        System.out.println("LinkedStack = " + linkedStack);
        System.out.println("linkedStack.peek(): " + linkedStack.peek());
        System.out.println("linkedStack.pop(): " + linkedStack.pop());
        System.out.println("After pop: " + linkedStack);
        System.out.println();
        linkedStack.push(6);
        System.out.println("LinkedStack = " + linkedStack);
        System.out.println("linkedStack.peek(): " + linkedStack.peek());
        System.out.println("linkedStack.pop(): " + linkedStack.pop());
        System.out.println("After pop: " + linkedStack);
        System.out.println();
        linkedStack.push(7);
        System.out.println("LinkedStack = " + linkedStack);
        System.out.println("linkedStack.peek(): " + linkedStack.peek());
        System.out.println("linkedStack.pop(): " + linkedStack.pop());
        System.out.println("After pop: " + linkedStack);
        System.out.println();
        linkedStack.push(8);
        System.out.println("LinkedStack = " + linkedStack);
        System.out.println("linkedStack.peek(): " + linkedStack.peek());
        System.out.println("linkedStack.pop(): " + linkedStack.pop());
        System.out.println("After pop: " + linkedStack);
        System.out.println();
        linkedStack.push(9);
        System.out.println("LinkedStack = " + linkedStack);
        System.out.println("linkedStack.peek(): " + linkedStack.peek());
        System.out.println("linkedStack.pop(): " + linkedStack.pop());
        System.out.println("After pop: " + linkedStack);
        System.out.println();
        linkedStack.push(10);
        System.out.println("LinkedStack = " + linkedStack);
        System.out.println("linkedStack.peek(): " + linkedStack.peek());
        System.out.println("linkedStack.pop(): " + linkedStack.pop());
        System.out.println("After pop: " + linkedStack);
    }
}

