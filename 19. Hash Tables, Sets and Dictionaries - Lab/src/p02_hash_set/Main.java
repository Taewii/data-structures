package p02_hash_set;

public class Main {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(5);
        set.add(2);
        set.add(11);
        set.add(8);
        set.add(34);

        HashSet<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(2);
        set2.add(9);
        set2.add(34);
        set2.add(8);
        set2.add(0);

        Iterable<Integer> newSet = set.symmetricExceptWith(set2);

        for (Integer integer : newSet) {
            System.out.print(integer + " ");
        }
    }
}
