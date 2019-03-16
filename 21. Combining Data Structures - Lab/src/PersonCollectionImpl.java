import java.util.*;

public class PersonCollectionImpl implements PersonCollection {

    private Map<String, Person> byEmail;
    private Map<String, SortedSet<Person>> byDomain;
    private Map<String, SortedSet<Person>> byNameAndTown;
    private SortedMap<Integer, SortedSet<Person>> byAge;
    private SortedMap<String, SortedSet<Person>> byTownAndAge;

    public PersonCollectionImpl() {
        this.byEmail = new HashMap<>();
        this.byDomain = new HashMap<>();
        this.byNameAndTown = new HashMap<>();
        this.byAge = new TreeMap<>();
        this.byTownAndAge = new TreeMap<>();
    }

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        if (this.byEmail.containsKey(email)) return false;

        Person person = new Person(email, name, age, town);
        this.byEmail.put(email, person);

        String domain = email.substring(email.indexOf('@') + 1);
        this.byDomain.putIfAbsent(domain, new TreeSet<>());
        this.byDomain.get(domain).add(person);

        String nameAndTown = name + town;
        this.byNameAndTown.putIfAbsent(nameAndTown, new TreeSet<>());
        this.byNameAndTown.get(nameAndTown).add(person);

        this.byAge.putIfAbsent(age, new TreeSet<>());
        this.byAge.get(age).add(person);

        String townAndAge = town + age;
        this.byTownAndAge.putIfAbsent(townAndAge, new TreeSet<>());
        this.byTownAndAge.get(townAndAge).add(person);
        return true;
    }

    @Override
    public int getCount() {
        return this.byEmail.size();
    }

    @Override
    public Person findPerson(String email) {
        return this.byEmail.getOrDefault(email, null);
    }

    @Override
    public boolean deletePerson(String email) {
        if (!this.byEmail.containsKey(email)) return false;

        Person person = this.byEmail.get(email);
        this.byEmail.remove(email);

        String domain = email.substring(email.indexOf('@') + 1);
        this.byDomain.get(domain).remove(person);

        String nameAndTown = person.getName() + person.getTown();
        this.byNameAndTown.get(nameAndTown).remove(person);

        this.byAge.get(person.getAge()).remove(person);

        String townAndAge = person.getTown() + person.getAge();
        this.byTownAndAge.get(townAndAge).remove(person);
        return true;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        return this.byDomain.getOrDefault(emailDomain, new TreeSet<>());
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        return this.byNameAndTown.getOrDefault(name + town, new TreeSet<>());
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        List<Person> people = new LinkedList<>();
        this.byAge.subMap(startAge, endAge + 1).values().forEach(people::addAll);
        return people;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        List<Person> people = new LinkedList<>();

        String start = town + startAge;
        String end = town + endAge + 1;
        this.byTownAndAge.subMap(start, end).values().forEach(set -> {
            set.forEach(person -> {
                if (person.getAge() >= startAge && person.getAge() <= endAge) {
                    people.add(person);
                }
            });
        });
        return people;
    }
}
