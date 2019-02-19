import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap;
    private int size;

    public BinaryHeap() {
        this.heap = new ArrayList<>();
    }

    public int size() {
        return this.size;
    }

    public void insert(T element) {
        this.size++;
        this.heap.add(element);
        shiftUp(this.size - 1);
    }

    private void shiftUp(int index) {
        int parentIndex = (index - 1) / 2;
        if (this.heap.get(parentIndex).compareTo(this.heap.get(index)) < 0) {
            T tmp = this.heap.get(parentIndex);
            this.heap.set(parentIndex, this.heap.get(index));
            this.heap.set(index, tmp);
            shiftUp(parentIndex);
        }
    }

    public T peek() {
        return this.heap.get(0);
    }

    public T pull() {
        if (this.heap.size() == 0) {
            throw new IllegalArgumentException();
        }

        T removedElement = this.heap.get(0);
        this.heap.set(0, this.heap.get(this.heap.size() - 1));
        this.heap.remove(this.heap.size() - 1);
        this.size--;

        if (this.size > 0) {
            shiftDown(0);
        }
        return removedElement;
    }

    private void shiftDown(int index) {
        int maxIndex;
        int leftChild = (2 * index) + 1;
        int rightChild = (2 * index) + 2;

        if (rightChild >= this.size) {
            if (leftChild >= this.size) {
                return;
            } else {
                maxIndex = leftChild;
            }
        } else {
            if (this.heap.get(leftChild).compareTo(this.heap.get(rightChild)) >= 0) {
                maxIndex = leftChild;
            } else {
                maxIndex = rightChild;
            }
        }

        if (this.heap.get(index).compareTo(this.heap.get(maxIndex)) < 0) {
            T temp = this.heap.get(maxIndex);
            this.heap.set(maxIndex, this.heap.get(index));
            this.heap.set(index, temp);
            shiftDown(maxIndex);
        }
    }
}
