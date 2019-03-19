package p01_organization;

import java.util.*;

public class Organization implements IOrganization {

    private List<Person> byInsertionOrder;
    private Map<String, List<Person>> byName;
    private NavigableMap<Integer, List<Person>> byNameLength;

    public Organization() {
        this.byName = new HashMap<>();
        this.byInsertionOrder = new ArrayList<>();
        this.byNameLength = new TreeMap<>();
    }

    @Override
    public int getCount() {
        return this.byInsertionOrder.size();
    }

    @Override
    public boolean contains(Person person) {
        if (!this.byName.containsKey(person.getName())) return false;
        return this.byName.get(person.getName()).contains(person);
    }

    @Override
    public boolean containsByName(String name) {
        return this.byName.containsKey(name);
    }

    @Override
    public void add(Person person) {
        this.byName.putIfAbsent(person.getName(), new ArrayList<>());
        this.byName.get(person.getName()).add(person);

        int length = person.getName().length();
        this.byNameLength.putIfAbsent(length, new ArrayList<>());
        this.byNameLength.get(length).add(person);

        this.byInsertionOrder.add(person);
    }

    @Override
    public Person getAtIndex(int index) {
        if (index < 0 || index >= this.byInsertionOrder.size()) {
            throw new IllegalArgumentException();
        }
        return this.byInsertionOrder.get(index);
    }

    @Override
    public Iterable<Person> getByName(String name) {
        return this.byName.getOrDefault(name, Collections.emptyList());
    }

    @Override
    public Iterable<Person> firstByInsertOrder() {
        return Collections.singletonList(this.byInsertionOrder.get(0));
    }

    @Override
    public Iterable<Person> firstByInsertOrder(int count) {
        if (this.byInsertionOrder.isEmpty()) return Collections.emptyList();
        if (count >= this.byInsertionOrder.size()) return this.byInsertionOrder;
        return this.byInsertionOrder.subList(0, count);
    }

    @Override
    public Iterable<Person> searchWithNameSize(int minLength, int maxLength) {
        List<Person> people = new ArrayList<>();

        minLength = this.byNameLength.ceilingKey(minLength);
        maxLength = this.byNameLength.floorKey(maxLength);
        this.byNameLength.subMap(minLength, true, maxLength, true)
                .forEach((k, v) -> people.addAll(v));

        return people;
    }

    @Override
    public Iterable<Person> getWithNameSize(int length) {
        if (!this.byNameLength.containsKey(length)) {
            throw new IllegalArgumentException();
        }
        return this.byNameLength.get(length);
    }

    @Override
    public Iterable<Person> peopleByInsertOrder() {
        return this.byInsertionOrder;
    }

    @Override
    public Iterator<Person> iterator() {
        return this.byInsertionOrder.iterator();
    }
}
