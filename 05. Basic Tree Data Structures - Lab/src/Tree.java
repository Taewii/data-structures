import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private T value;
    private Tree<T>[] children;

    @SafeVarargs
    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = children;
    }

    public String print(int indent, StringBuilder builder) {
        builder.append(repeatStr(" ", indent));
        builder.append(this.value).append("\n");
//        builder.append(this.value).append(System.lineSeparator());

        for (Tree<T> child : children) {
            builder.append(child.print(indent + 1, new StringBuilder()));
        }
        return builder.toString();
    }

    private String repeatStr(String str, int count) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < count * 2; i++) {
            text.append(str);
        }
        return text.toString();
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);
        for (Tree<T> child : children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {
        Deque<T> result = new ArrayDeque<>();
        Deque<Tree<T>> stack = new ArrayDeque<>();
        stack.push(this);
        while (!stack.isEmpty()) {
            Tree<T> current = stack.pop();
            for (Tree<T> child : current.children) {
                stack.push(child);
            }
            result.push(current.value);
        }
        return result;
    }

    public Iterable<T> orderBFS() {
        List<T> result = new LinkedList<>();
        Deque<Tree<T>> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            Tree<T> current = queue.poll();
            for (Tree<T> child : current.children) {
                queue.offer(child);
            }
            result.add(current.value);
        }
        return result;
    }
}