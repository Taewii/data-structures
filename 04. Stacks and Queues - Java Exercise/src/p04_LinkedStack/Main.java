package p04_LinkedStack;

public class Main {
    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        Integer[] integers = stack.toArray();
    }
}
