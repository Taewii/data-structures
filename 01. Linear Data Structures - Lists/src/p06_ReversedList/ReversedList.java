package p06_ReversedList;

import java.util.Arrays;
import java.util.Iterator;

public class ReversedList<T> implements Iterable<T> {
    private final int initialCapacity = 2;
    private T[] data;
    private int count;

    public ReversedList() {
        this.data = createArray(initialCapacity);
    }

    @SuppressWarnings("unchecked")
    private T[] createArray(int initialCapacity) {
        return (T[]) new Object[initialCapacity];
    }

    public void add(T element) {
        if (this.data.length == this.count) {
            resize();
        }

        for (int i = this.count; i > 0; i--) {
            this.data[i] = this.data[i - 1];
        }
        this.data[0] = element;
        this.count++;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException();
        }

        this.count--;
        for (int i = index; i < this.count; i++) {
            this.data[index] = this.data[index + 1];
        }
    }

    private void resize() {
        this.data = Arrays.copyOf(this.data, this.count * 2);
    }

    public T get(int index) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException();
        }
        return this.data[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index >= this.count) {
            throw new IndexOutOfBoundsException();
        }
        this.data[index] = element;
    }

    public int count() {
        return this.count;
    }

    public int capacity() {
        return this.data.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseListIterator();
    }

    private class ReverseListIterator implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < count;
        }

        @Override
        public T next() {
            return data[cursor++];
        }
    }
}
