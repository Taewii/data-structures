package p03_ArrayStack;

import java.util.Arrays;

public class ArrayStack<T> {
    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;
    private int size;

    public ArrayStack() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
    }

    public ArrayStack(int capacity) {
        this.elements = (T[]) new Object[capacity];
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(T element) {
        if (this.size == this.elements.length) {
            this.grow();
        }

        this.elements[this.size++] = element;
    }

    public T pop() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        return this.elements[--this.size];
    }

    public T[] toArray() {
        int internalIndex = this.size - 1;
        T[] array = (T[]) new Object[this.size];
        int arrayIndex = 0;
        while (internalIndex >= 0) {
            array[arrayIndex++] = this.elements[internalIndex--];
        }
        return array;  //TODO: figure out the ClassCastException
    }

    private void grow() {
        this.elements = Arrays.copyOf(this.elements, this.elements.length * 2);
    }
}