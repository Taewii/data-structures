package p02_first_last_list;

import java.util.ArrayList;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {
    private ArrayList<T> elements = new ArrayList<T>();

    @Override
    public void add(T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<T> first(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<T> last(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<T> min(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<T> max(int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int removeAll(T element) {
        throw new UnsupportedOperationException();
    }
}