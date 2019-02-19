public class Heap {

    public static <E extends Comparable<E>> void sort(E[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            E temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private static <E extends Comparable<E>> void heapify(E[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }

        if (right < n && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            E temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }
}
