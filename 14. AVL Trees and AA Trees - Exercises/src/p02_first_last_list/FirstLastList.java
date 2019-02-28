package p02_first_last_list;

import java.util.*;
import java.util.stream.Collectors;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {
    private List<T> elements;

    public FirstLastList() {
        this.elements = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        this.elements.add(element);
    }

    @Override
    public int getCount() {
        return this.elements.size();
    }

    @Override
    public Iterable<T> first(int count) {
        if (count > this.elements.size()) {
            throw new IllegalArgumentException();
        }
        return this.elements.subList(0, count);
    }

    @Override
    public Iterable<T> last(int count) {
        if (count > this.elements.size()) {
            throw new IllegalArgumentException();
        }

        Deque<T> stack = new ArrayDeque<>();
        for (int i = this.elements.size() - count; i < this.elements.size(); i++) {
            stack.push(this.elements.get(i));
        }

        return stack;
    }

    @Override
    public Iterable<T> min(int count) {
        if (count > this.elements.size()) {
            throw new IllegalArgumentException();
        }
        return this.elements.stream().sorted(Comparable::compareTo).limit(count).collect(Collectors.toList());
    }

    @Override
    public Iterable<T> max(int count) {
        if (count > this.elements.size()) {
            throw new IllegalArgumentException();
        }
        return this.elements.stream().sorted(Comparator.reverseOrder()).limit(count).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        this.elements.clear();
    }

    @Override
    public int removeAll(T element) {
        int originalLength = this.elements.size();
        for (int i = 0; i < this.elements.size(); i++) {
            if (this.elements.get(i).compareTo(element) == 0) {
                this.elements.remove(i);
                i--;
            }
        }
        return originalLength - this.elements.size();
    }
}