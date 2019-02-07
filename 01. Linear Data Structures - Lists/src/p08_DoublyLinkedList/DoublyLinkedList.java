package p08_DoublyLinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

public class DoublyLinkedList<E> implements Iterable<E> {

    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E element) {
        throw new UnsupportedOperationException();
    }

    public void addLast(E element) {
        throw new UnsupportedOperationException();
    }

    public E removeFirst() {
        throw new UnsupportedOperationException();
    }

    public E removeLast() {
        throw new UnsupportedOperationException();
    }

    public E[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        throw new UnsupportedOperationException();
    }

}
