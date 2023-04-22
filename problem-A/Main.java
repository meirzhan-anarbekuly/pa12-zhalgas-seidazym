class Node<E> {
    E element;
    Node<E> next;

    public Node(E element) {
        this.element = element;
    }
}

class MyLL<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public void print() {
        Node<E> current = head;
        System.out.print("[");
        String s = "";
        while (current != null) {
            s += current.element + ", ";
            current = current.next;
        }
        s = s.substring(0, s.length() - 2) + "]";
        System.out.println(s);
    }

    public E get(int index) {
        checkIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }

    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> newNode = new Node<>(e);
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public E removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Node<E> temp = head;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return temp.element;
        }
    }

    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> previous = head;
            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }
            Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }

    public E removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }
            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }

    public void set(int index, E e) {
        checkIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.element = e;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public static void main(String[] args) {
        MyLL<String> countries = new MyLL<>();
        System.out.println("countries.empty(): " + countries.isEmpty());
        System.out.println("Add first: 'China'");countries.addFirst("China");
        System.out.println("Add first: 'Germany");countries.addFirst("Germany");
        System.out.println("countries.getFirst(): " + countries.getFirst());
        System.out.println("Add first: 'Kazakhstan'");countries.addFirst("Kazakhstan");
        countries.print();
        System.out.println("countries.empty(): " + countries.isEmpty());
        System.out.println("countries.size(): " + countries.size());
        System.out.println("Add last: 'USA'");countries.addLast("USA");
        countries.print();
        System.out.println("countries.getLast(): " + countries.getLast());
        System.out.println("Add index-2: 'Albania'");countries.add(2, "Albania");
        countries.print();
        System.out.println("countries.get(2):" + countries.get(2));
        System.out.println("countries.getFirst(): " + countries.getFirst());
        System.out.println("Remove first:");
        countries.removeFirst();
        countries.print();
        System.out.println("Remove last:");
        countries.removeLast();
        countries.print();
        System.out.println("Remove index - 1 element:");
        countries.remove(1);
        countries.print();
    }
}
