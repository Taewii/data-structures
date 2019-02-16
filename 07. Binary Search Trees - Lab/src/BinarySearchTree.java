import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node node) {
        this.root = node;
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(T element) {
        if (this.root == null) {
            this.root = new Node(element);
            return;
        }

        Node current = this.root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (current.value.compareTo(element) > 0) {
                current = current.left;
            } else if (current.value.compareTo(element) < 0) {
                current = current.right;
            } else {
                break;
            }
        }

        current = new Node(element);
        if (parent.value.compareTo(element) < 0) {
            parent.right = current;
        } else {
            parent.left = current;
        }
    }

    public boolean contains(T element) {
        Node current = this.root;

        while (current != null) {
            if (current.value.compareTo(element) > 0) {
                current = current.left;
            } else if (current.value.compareTo(element) < 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return current != null;
    }

    public BinarySearchTree<T> search(T element) {
        Node current = this.root;

        while (current != null) {
            if (current.value.compareTo(element) > 0) {
                current = current.left;
            } else if (current.value.compareTo(element) < 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }

    public Iterable<T> range(T from, T to) {
        Queue<T> range = new ArrayDeque<>();
        this.range(from, to, range, this.root);
        return range;
    }

    private void range(T from, T to, Queue<T> range, Node node) {
        if (node == null) {
            return;
        }

        int lowBound = from.compareTo(node.value);
        int highBound = to.compareTo(node.value);

        if (lowBound < 0) {
            this.range(from, to, range, node.left);
        } else if (lowBound >= 0 && highBound <= 0) {
            range.offer(node.value);
        } else if (highBound > 0) {
            this.range(from, to, range, node.right);
        }
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}

