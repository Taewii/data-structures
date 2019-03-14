package hashtable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashTable<TKey, TValue> implements Iterable<KeyValue<TKey, TValue>> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private int size;
    private int capacity;
    private int maxElements;
    private List<KeyValue<TKey, TValue>>[] hashTable;

    @SuppressWarnings("unchecked")
    public HashTable(int... capacity) {
        this.hashTable = capacity.length == 0 ? new LinkedList[DEFAULT_CAPACITY] : new LinkedList[capacity[0]];
        this.capacity = this.hashTable.length;
        this.maxElements = (int) (this.capacity * LOAD_FACTOR);
    }

    public void add(TKey key, TValue value) {

        this.resizeIfNeeded();
        int hash = Math.abs(key.hashCode() % this.capacity);

        if (this.hashTable[hash] == null) {
            this.hashTable[hash] = new LinkedList<>();
        }

        for (KeyValue<TKey, TValue> keyValue : this.hashTable[hash]) {
            if (keyValue.getKey().equals(key)) {
                throw new IllegalArgumentException("Key already exists.");
            }
        }

        this.hashTable[hash].add(new KeyValue<>(key, value));
        this.size++;
    }

    private void resizeIfNeeded() {
        if (this.size < this.maxElements) {
            return;
        }

        HashTable<TKey, TValue> newTable = new HashTable<>(this.capacity * 2);
        for (List<KeyValue<TKey, TValue>> keyValues : this.hashTable) {
            if (keyValues != null) {
                for (KeyValue<TKey, TValue> keyValue : keyValues) {
                    newTable.add(keyValue.getKey(), keyValue.getValue());
                }
            }
        }

        this.hashTable = newTable.hashTable;
        this.size = newTable.size;
        this.capacity = newTable.capacity;
        this.maxElements = (int) (this.capacity * LOAD_FACTOR);
    }

    public int size() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean addOrReplace(TKey key, TValue value) {
        KeyValue<TKey, TValue> keyValue = this.find(key);
        if (keyValue != null) {
            keyValue.setValue(value);
            return true;
        }

        this.add(key, value);
        return true;
    }

    public TValue get(TKey key) {
        KeyValue<TKey, TValue> keyValue = this.find(key);
        if (keyValue == null) {
            throw new IllegalArgumentException();
        }
        return keyValue.getValue();
    }

    public boolean tryGetValue(TKey key, TValue value) {
        return this.find(key) != null;
    }

    public KeyValue<TKey, TValue> find(TKey key) {
        int hash = Math.abs(key.hashCode() % this.capacity);

        if (this.hashTable[hash] != null) {
            for (KeyValue<TKey, TValue> keyValue : this.hashTable[hash]) {
                if (keyValue.getKey().equals(key)) {
                    return keyValue;
                }
            }
        }

        return null;
    }

    public boolean containsKey(TKey key) {
        return this.find(key) != null;
    }

    public boolean remove(TKey key) {
        int hash = Math.abs(key.hashCode() % this.capacity);

        if (this.hashTable[hash] != null) {
            for (KeyValue<TKey, TValue> keyValue : this.hashTable[hash]) {
                if (keyValue.getKey().equals(key)) {
                    this.hashTable[hash].remove(keyValue);
                    this.size--;
                    return true;
                }
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        this.capacity = DEFAULT_CAPACITY;
        this.hashTable = new LinkedList[this.capacity];
        this.setSize(0);
    }

    public Iterable<TKey> keys() {
        List<TKey> keys = new LinkedList<>();
        this.forEach(e -> keys.add(e.getKey()));
        return keys;
    }

    public Iterable<TValue> values() {
        List<TValue> keys = new LinkedList<>();
        this.forEach(e -> keys.add(e.getValue()));
        return keys;
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {
        List<KeyValue<TKey, TValue>> elements = new LinkedList<>();
        for (List<KeyValue<TKey, TValue>> keyValueList : this.hashTable) {
            if (keyValueList != null) {
                elements.addAll(keyValueList);
            }
        }
        return elements.iterator();
    }
}
