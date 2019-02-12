package p05_LinkedQueue;

public class Main {
    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.toArray().toString()); //TODO: figure out the ClassCastException
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
