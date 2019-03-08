package p02_quad_tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuadTree<T extends Boundable> {

    private static final int DEFAULT_MAX_DEPTH = 5;
    private int maxDepth;
    private Node<T> root;
    private Rectangle bounds;
    private int count;

    public QuadTree(int width, int height) {
        this(width, height, DEFAULT_MAX_DEPTH);
    }

    public QuadTree(int width, int height, int maxDepth) {
        this.root = new Node<T>(0, 0, width, height);
        this.bounds = this.root.getBounds();
        this.maxDepth = maxDepth;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    private void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getCount() {
        return this.count;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public boolean insert(T item) {
        Node<T> current = this.root;
        int depth = 1;

        while (current.getChildren() != null) {
            int quadrant = getQuadrant(current, item.getBounds());
            if (quadrant == -1) break;
            current = current.getChildren()[quadrant];
            depth++;
        }

        current.getItems().add(item);
        trySplit(current, depth);
        this.count++;
        return true;
    }

    @SuppressWarnings("unchecked")
    private void trySplit(Node<T> node, int depth) {
        if (!node.shouldSplit() || depth >= this.maxDepth) {
            return;
        }

        int leftWidth = node.getBounds().getWidth() / 2;
        int rightWidth = node.getBounds().getWidth() - leftWidth;
        int topHeight = node.getBounds().getHeight() / 2;
        int botHeight = node.getBounds().getHeight() - topHeight;

        node.setChildren(new Node[4]);
        node.getChildren()[0] = new Node<>(node.getBounds().getMidX(), node.getBounds().getY1(), rightWidth, topHeight);
        node.getChildren()[1] = new Node<>(node.getBounds().getX1(), node.getBounds().getY1(), leftWidth, topHeight);
        node.getChildren()[2] = new Node<>(node.getBounds().getX1(), node.getBounds().getMidY(), leftWidth, botHeight);
        node.getChildren()[3] = new Node<>(node.getBounds().getMidX(), node.getBounds().getMidY(), rightWidth, botHeight);

        Set<T> toRemove = new HashSet<>();
        for (T item : node.getItems()) {
            int quadrant = getQuadrant(node, item.getBounds());
            if (quadrant != -1) {
                node.getChildren()[quadrant].getItems().add(item);
                toRemove.add(item);
            }
        }
        node.getItems().removeIf(toRemove::contains);

        for (Node<T> child : node.getChildren()) {
            this.trySplit(child, depth + 1);
        }
    }

    private int getQuadrant(Node<T> node, Rectangle bounds) {
        if (node.getChildren() != null) {
            if (bounds.isInside(node.getChildren()[0].getBounds())) return 0;
            if (bounds.isInside(node.getChildren()[1].getBounds())) return 1;
            if (bounds.isInside(node.getChildren()[2].getBounds())) return 2;
            if (bounds.isInside(node.getChildren()[3].getBounds())) return 3;
        }
        return -1;
    }

    public List<T> report(Rectangle bounds) {
        List<T> pointsInRange = new ArrayList<>();
        this.getPotentialCollisions(this.root, bounds, pointsInRange);
        return pointsInRange;
    }

    private void getPotentialCollisions(Node<T> node, Rectangle bounds, List<T> pointsInRange) {
        int quadrant = getQuadrant(node, bounds);

        if (quadrant != -1) {
            pointsInRange.addAll(node.getItems());
            this.getPotentialCollisions(node.getChildren()[quadrant], bounds, pointsInRange);
        } else {
            this.getItemsFromNode(node, pointsInRange);
        }
    }

    private void getItemsFromNode(Node<T> node, List<T> pointsInRange) {
        if (node == null) return;
        pointsInRange.addAll(node.getItems());

        if (node.getChildren() != null) {
            for (Node<T> child : node.getChildren()) {
                this.getItemsFromNode(child, pointsInRange);
            }
        }
    }
}
