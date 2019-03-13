package p02_hash_set;

import p01_hash_table.HashTable;

import java.util.Iterator;

public class HashSet<T> implements Iterable<T> {

    private HashTable<T, T> set;

    public HashSet() {
        this.set = new HashTable<>();
    }

    public void add(T element) {
        this.set.addOrReplace(element, element);
    }

    @Override
    public Iterator<T> iterator() {
        return this.set.values().iterator();
    }

    public Iterable<T> unionWith(HashSet<T> other) {
        HashSet<T> newSet = new HashSet<>();

        for (T item : this.set.values()) {
            newSet.add(item);
        }

        for (T item : other) {
            newSet.add(item);
        }

        return newSet;
    }

    public Iterable<T> intersectsWith(HashSet<T> other) {
        HashSet<T> newSet = new HashSet<>();

        for (T item : this.set.values()) {
            if (other.set.containsKey(item)) {
                newSet.add(item);
            }
        }

        return newSet;
    }

    public Iterable<T> exceptWith(HashSet<T> other) {
        HashSet<T> newSet = new HashSet<>();

        for (T item : this.set.values()) {
            if (!other.set.containsKey(item)) {
                newSet.add(item);
            }
        }

        return newSet;
    }

    public Iterable<T> symmetricExceptWith(HashSet<T> other) {
        HashSet<T> newSet = new HashSet<>();

        for (T item : this.set.values()) {
            if (!other.set.containsKey(item)) {
                newSet.add(item);
            }
        }

        for (T item : other) {
            if (!this.set.containsKey(item)) {
                newSet.add(item);
            }
        }

        return newSet;
    }
}
