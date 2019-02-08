package p08_DoublyLinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

public class DoublyLinkedList<E> implements Iterable<E> {

    private class Node {
        private E value;
        private Node previous;
        private Node next;

        public Node(E value) {
            this.value = value;
        }

        public Node getPrevious() {
            return this.previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E element) {
        Node newHead = new Node(element);

        if (this.size == 0) {
            this.head = newHead;
            this.tail = newHead;
        } else {
            newHead.setNext(this.head);
            this.head.setPrevious(newHead);
            this.head = newHead;
        }

        this.size++;
    }

    public void addLast(E element) {
        Node newTail = new Node(element);

        if (this.size == 0) {
            this.head = newTail;
            this.tail = newTail;
        } else {
            newTail.setPrevious(this.tail);
            this.tail.setNext(newTail);
            this.tail = newTail;
        }

        this.size++;
    }

    public E removeFirst() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        Node oldHead = this.head;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = oldHead.getNext();
            this.head.setPrevious(null);
        }
        this.size--;
        return oldHead.value;
    }

    public E removeLast() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        Node oldTail = this.tail;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = oldTail.getPrevious();
            this.tail.setNext(null);
        }
        this.size--;
        return oldTail.value;
    }

    @SuppressWarnings("unchecked")
    public E[] toArray() {
        E[] elements = (E[]) new Object[this.size];

        int counter = 0;
        Node node = this.head;

        while (node != null) {
            elements[counter] = node.value;
            node = node.getNext();
            counter++;
        }
        return elements;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements Iterator {
        Node node = head;

        @Override
        public boolean hasNext() {
            return this.node != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new IllegalArgumentException();
            }
            E tmp = this.node.value;
            this.node = this.node.next;
            return tmp;
        }
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Node node = this.head;
        while (node != null) {
            action.accept(node.value);
            node = node.getNext();
        }
    }
}
