package p04_LinkedStack;

public class LinkedStack<E> {
    private Node<E> firstNode;
    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        this.firstNode = new Node<>(element, this.firstNode);
        this.size++;
    }

    public E pop() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        E value = this.firstNode.value;
        this.firstNode = this.firstNode.getNextNode();
        this.size--;
        return value;
    }

    public E[] toArray() {
        E[] arr = (E[]) new Object[this.size];

        int index = 0;
        Node current = this.firstNode;
        while (current != null) {
            arr[index++] = (E) current.value;
            current = current.nextNode;
        }
        return arr; //TODO: figure out the ClassCastException
    }

    private class Node<E> {

        private E value;
        private Node<E> nextNode;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }
}