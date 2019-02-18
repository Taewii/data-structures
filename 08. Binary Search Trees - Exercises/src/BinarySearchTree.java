import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    private int nodesCount;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node root) {
        this.preOrderCopy(root);
    }

    private void preOrderCopy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public int getNodesCount() {
        return this.nodesCount;
    }

    public void insert(T value) {
        this.nodesCount++;

        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        Node parent = null;
        Node current = this.root;
        while (current != null) {
            parent = current;
            parent.childrenCount++;

            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                return;
            }
        }

        Node newNode = new Node(value);
        if (value.compareTo(parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    public boolean contains(T value) {
        Node current = this.root;
        while (current != null) {
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return current != null;
    }

    public BinarySearchTree<T> search(T item) {
        Node current = this.root;
        while (current != null) {
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new LinkedList<>();
        this.range(this.root, queue, from, to);
        return queue;
    }

    private void range(Node node, Deque<T> queue, T startRange, T endRange) {
        if (node == null) {
            return;
        }

        int compareStart = startRange.compareTo(node.value);
        int compareEnd = endRange.compareTo(node.value);
        if (compareStart < 0) {
            this.range(node.left, queue, startRange, endRange);
        }
        if (compareStart <= 0 && compareEnd >= 0) {
            queue.addLast(node.value);
        }
        if (compareEnd > 0) {
            this.range(node.right, queue, startRange, endRange);
        }
    }

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }

        return minv;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node min = this.root;
        Node parent = null;

        while (min.left != null) {
            parent = min;
            parent.childrenCount--;
            min = min.left;
        }

        if (parent == null) {
            this.root = this.root.right;
        } else {
            parent.left = min.right;
        }

        this.nodesCount--;
    }

    private T maxValue(Node root) {
        T maxValue = root.value;
        while (root.right != null) {
            maxValue = root.value;
            root = root.right;
        }

        return maxValue;
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node max = this.root;
        Node parent = null;

        while (max.right != null) {
            parent = max;
            parent.childrenCount--;
            max = max.right;
        }

        if (parent == null) {
            this.root = this.root.left;
        } else {
            parent.right = max.left;
        }

        this.nodesCount--;
    }

    public T ceil(T element) {
        return this.ceil(this.root, element);
    }

    private T ceil(Node node, T target) {
        if (node == null) {
            return null;
        }

        int compare = target.compareTo(node.value);
        if (compare > 0) {
            return this.ceil(node.right, target);
        }

        T result = ceil(node.left, target);
        if (result == null) {
            return node.value;
        }

        return result;
    }

    public T floor(T element) {
        return this.floor(this.root, element);
    }

    private T floor(Node node, T target) {
        if (node == null) {
            return null;
        }

        int compare = target.compareTo(node.value);
        if (compare < 0) {
            return this.floor(node.left, target);
        }

        T result = floor(node.right, target);
        if (result == null) {
            return node.value;
        }

        return result;
    }

    public void delete(T key) {
        Node node = this.deleteRecursively(this.root, key);
    }

    private Node deleteRecursively(Node node, T key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.value) < 0)
            node.left = deleteRecursively(node.left, key);
        else if (key.compareTo(node.value) > 0)
            node.right = deleteRecursively(node.right, key);
        else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.value = minValue(node.right);
            node.right = deleteRecursively(node.right, node.value);
        }
        return node;
    }

    public int rank(T item) {
        if (this.root == null) {
            return 0;
        }

        int count = 0;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.value.compareTo(item) < 0) {
                count++;
            }

            if (current.right != null) {
                queue.offer(current.right);
            }

            if (current.left != null) {
                queue.offer(current.left);
            }
        }
        return count;
    }

    public T select(int n) {
        Node node = findNodeWithNElementsLessThanN(this.root, 0, n);
        return node.value;
    }

    private Node findNodeWithNElementsLessThanN(Node node, int count, int target) {
        if (node == null) {
            return null;
        }

        if (count == target) {
            return node;
        }

        return findNodeWithNElementsLessThanN(node.right, count + 1, target);
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        private int childrenCount;

        public Node(T value) {
            this.value = value;
            this.childrenCount = 1;
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

        @Override
        public String toString() {
            return this.value + "";
        }
    }
}