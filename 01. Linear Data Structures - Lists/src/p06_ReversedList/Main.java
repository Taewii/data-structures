package p06_ReversedList;

public class Main {
    public static void main(String[] args) {
        ReversedList<Integer> list = new ReversedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.set(2, 12);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
