import java.util.Iterator;

public class HashTable<TKey, TValue> implements Iterable<KeyValue<TKey, TValue>> {

    private int size;

    private int capacity;

    public HashTable() {
        throw new UnsupportedOperationException();
    }

    public HashTable(int capacity) {
        throw new UnsupportedOperationException();
    }

    public void add(TKey key, TValue value) {
        throw new UnsupportedOperationException();
        // Note: throw an exception on duplicated key
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
        throw new UnsupportedOperationException();
    }

    public TValue get(TKey key) {
        throw new UnsupportedOperationException();
        // Note: throw an exception on missing key
    }

    public boolean tryGetValue(TKey key, TValue value) {
        throw new UnsupportedOperationException();
    }

    public KeyValue<TKey, TValue> find(TKey key) {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(TKey key) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(TKey key) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public Iterable<TKey> keys() {
        throw new UnsupportedOperationException();
    }

    public Iterable<TValue> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {
        return null;
    }
}
