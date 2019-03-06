package e02_k_d_tree;

import java.util.function.Consumer;

public class KdTree {

    public class Node {

        private Point2D point2D;
        private Node left;
        private Node right;

        public Node(Point2D point) {
            this.setPoint2D(point);
        }

        public Point2D getPoint2D() {
            return this.point2D;
        }

        public void setPoint2D(Point2D point2D) {
            this.point2D = point2D;
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

    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public boolean contains(Point2D point) {
        Node node = findPoint(this.root, point, 0);
        return node != null;
    }

    private Node findPoint(Node node, Point2D point, int depth) {
        if (node == null) return null;

        int cmp = this.compare(point, node.point2D, depth);
        if (cmp < 0) {
            return findPoint(node.left, point, depth + 1);
        } else if (cmp > 0){
            return findPoint(node.right, point, depth + 1);
        }

        return node;
    }

    public void insert(Point2D point) {
        this.root = insert(this.root, point, 0);
    }

    private Node insert(Node node, Point2D point, int depth) {
        if (node == null) {
            return new Node(point);
        }

        int cmp = compare(point, node.point2D, depth);
        if (cmp < 0) {
            node.left = this.insert(node.left, point, depth + 1);
        } else if (cmp > 0) {
            node.right = this.insert(node.right, point, depth + 1);
        }

        return node;
    }

    private int compare(Point2D a, Point2D b, int depth) {
        double cmp;
        if (depth % 2 == 0) {
            cmp = a.getX() - b.getX();
            if (cmp == 0) {
                cmp = a.getY() - b.getY();
            }
        } else {
            cmp = a.getY() - b.getY();
            if (cmp == 0) {
                cmp = a.getX() - b.getX();
            }
        }

        return (int) cmp;
    }

    public void eachInOrder(Consumer<Point2D> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<Point2D> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.getLeft(), consumer);
        consumer.accept(node.getPoint2D());
        this.eachInOrder(node.getRight(), consumer);
    }
}
