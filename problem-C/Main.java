import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.ListIterator;

class LinkedStack_C<E> implements Iterable<E> {
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

    public ListIterator<E> listIterator() {
        return new StackListIterator();
    }

    private class StackListIterator implements ListIterator<E> {
        private boolean canRemove = false;
        private int previousLoc = -1;
        private Node<E> current = top;

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            E element = current.element;
            previousLoc++;
            canRemove = true;
            current = current.next;
            return element;
        }

        public boolean hasPrevious() {
            return previousLoc >= 0;
        }

        public E previous() {
            if (!hasPrevious()) {
                throw new java.util.NoSuchElementException();
            }
            previousLoc--;
            canRemove = true;
            if (current == null) {
                current = top;
                for (int i = 0; i < previousLoc; i++) {
                    current = current.next;
                }
            } else {
                current = current.next;
            }
            return current.element;
        }

        public int nextIndex() {
            return previousLoc + 1;
        }

        public int previousIndex() {
            return previousLoc;
        }

        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException();
            }
            if (previousLoc < 0) {
                throw new IllegalStateException();
            }
            LinkedStack_C.this.pop();
            previousLoc--;
        }

        public void set(E e) {
            if (!canRemove) {
                throw new IllegalStateException();
            }
            if (previousLoc < 0) {
                throw new IllegalStateException();
            }
            Node<E> newNode = new Node<>(e, null);
            if (previousLoc == 0) {
                newNode.next = top.next;
                top = newNode;
            } else {
                Node<E> current = top;
                for (int i = 0; i < previousLoc - 1; i++) {
                    current = current.next;
                }
                newNode.next = current.next.next;
                current.next = newNode;
            }
        }

        public void add(E e) {
            if (current == null) {
                push(e);
                current = top;
                previousLoc++;
            } else {
                Node<E> newNode = new Node<>(e, current.next);
                current.next = newNode;
                current = newNode;
                previousLoc++;
                size++;
                canRemove = false;
            }
        }
    }


    public static void main(String[] args) {
        LinkedStack_C<String> stack = new LinkedStack_C<>();
        stack.push("one");
        stack.push("two");
        stack.push("three");
        System.out.println("Stack: " + stack); 
        System.out.println("Stack size: " + stack.size()); 
        System.out.println("Peek: " + stack.peek()); 
        System.out.println("Pop: " + stack.pop()); 
        System.out.println("Stack: " + stack);
        System.out.println("Using iterator to print stack: ");
        for (String s : stack) {
            System.out.print(s + " ");
        } 
        System.out.println();
        System.out.println("Using list iterator to add and remove elements: ");
        ListIterator<String> iter = stack.listIterator();
        while (iter.hasNext()) {
            String s = iter.next();
            if (s.equals("two")) {
                iter.add("two-and-a-half");
            }
            if (s.equals("one")) {
                iter.remove();
            }
        }
        System.out.println("Stack after list iterator changes: " + stack); 
    }

}
