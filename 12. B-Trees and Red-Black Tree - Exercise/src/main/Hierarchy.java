package main;

import java.util.*;
import java.util.stream.Collectors;

public class Hierarchy<T> implements IHierarchy<T> {

    private Node<T> root;
    private Map<T, Node<T>> nodes;

    public Hierarchy(T element) {
        this.root = new Node<>(element);
        this.nodes = new LinkedHashMap<>();
        this.nodes.put(element, this.root);
    }

    public void add(T parent, T child) {
        if (!this.nodes.containsKey(parent)) {
            throw new IllegalArgumentException("Parent doesn't exist.");
        }

        if (this.nodes.containsKey(child)) {
            throw new IllegalArgumentException("Child node already exists.");
        }

        Node<T> parentNode = this.nodes.get(parent);
        Node<T> childNode = new Node<>(child);
        childNode.setParent(parentNode);
        parentNode.addChild(childNode);
        this.nodes.put(child, childNode);
    }

    public int getCount() {
        return this.nodes.size();
    }

    public void remove(T element) {
        if (element == this.root.getValue()) {
            throw new IllegalStateException("Cannot remove root.");
        }

        if (!this.nodes.containsKey(element)) {
            throw new IllegalArgumentException("Element doesn't exist.");
        }

        Node<T> node = this.nodes.get(element);
        Node<T> parent = node.getParent();
        parent.getChildrenAsNodes().remove(node);
        node.getChildrenAsNodes().forEach(child -> {
            child.setParent(parent);
            parent.addChild(child);
        });

        node.children.clear();
        node.parent = null;
        this.nodes.remove(element);
    }

    public boolean contains(T element) {
        return this.nodes.containsKey(element);
    }

    public T getParent(T element) {
        if (!this.nodes.containsKey(element)) {
            throw new IllegalArgumentException("Element doesn't exist.");
        }

        Node<T> parent = this.nodes.get(element).getParent();
        return parent == null ? null : parent.getValue();
    }

    public Iterable<T> getChildren(T element) {
        if (!this.nodes.containsKey(element)) {
            throw new IllegalArgumentException("Element doesn't exist.");
        }

        return this.nodes.get(element).getChildren();
    }

    public Iterable<T> getCommonElements(IHierarchy<T> other) {
        List<T> elements = new LinkedList<>();
        this.nodes.keySet().forEach(key -> {
            if (other.contains(key)) {
                elements.add(key);
            }
        });

        return elements;
    }

    @Override
    public Iterator<T> iterator() {
        Deque<Node<T>> queue = new ArrayDeque<>();
        List<T> allElements = new ArrayList<>();
        queue.push(this.root);
        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            allElements.add(currentNode.getValue());
            queue.addAll(currentNode.getChildrenAsNodes());
        }
        return allElements.iterator();
    }

    private class Node<T> {
        private T value;
        private Node<T> parent;
        private List<Node<T>> children;

        Node(T value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        T getValue() {
            return this.value;
        }

        public Node<T> getParent() {
            return this.parent;
        }

        void setParent(Node<T> parent) {
            this.parent = parent;
        }

        void addChild(Node<T> node) {
            this.children.add(node);
        }

        public Iterable<T> getChildren() {
            return this.children.stream().map(Node::getValue).collect(Collectors.toList());
        }

        public List<Node<T>> getChildrenAsNodes() {
            return this.children;
        }
    }
}
