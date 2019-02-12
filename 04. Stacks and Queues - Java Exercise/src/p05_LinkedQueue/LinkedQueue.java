package p05_LinkedQueue;

public class LinkedQueue<E> {

    private int size;
    private QueueNode<E> head;
    private QueueNode<E> tail;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        QueueNode<E> newNode = new QueueNode<>(element);

        if (this.size == 0) {
            this.head = this.tail = newNode;
        } else {
            newNode.setPrevNode(this.tail);
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
        this.size++;
    }

    public E dequeue() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        E value = this.head.getValue();
        QueueNode<E> nextNode = this.head.getNextNode();
        this.head.setNextNode(null);
        this.head = nextNode;
        this.size--;
        return value;
    }

    public E[] toArray() {
        E[] arr = (E[]) new Object[this.size];
        QueueNode<E> current = this.head;
        int index = 0;
        while (current != null) {
            arr[index++] = current.getValue();
            current = current.getNextNode();
        }

        return arr; //TODO: figure out the ClassCastException
    }

    private class QueueNode<E> {
        private E value;

        private QueueNode<E> nextNode;
        private QueueNode<E> prevNode;

        public QueueNode(E value) {
            this.value = value;
        }

        public E getValue() {
            return this.value;
        }

        private void setValue(E value) {
            this.value = value;
        }

        public QueueNode<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(QueueNode<E> nextNode) {
            this.nextNode = nextNode;
        }

        public QueueNode<E> getPrevNode() {
            return this.prevNode;
        }

        public void setPrevNode(QueueNode<E> prevNode) {
            this.prevNode = prevNode;
        }
    }
}