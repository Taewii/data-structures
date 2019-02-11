package p03_ArrayStack;

public class Main {
    public static void main(String[] args) {
        ArrayStack<Integer> ats = new ArrayStack<>();
        ats.push(1);
        ats.push(2);
        ats.push(3);

        // ClassCastException ...
        Integer[] integers = ats.toArray();
        System.out.println(ats.size());
        System.out.println(ats.pop());
        System.out.println(ats.size());
    }
}
