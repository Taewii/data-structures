import java.util.*;

public class PersonCollectionImpl implements PersonCollection {

    private Map<String, Person> byEmail;
    private Map<String, Set<Person>> byDomain;

    public PersonCollectionImpl() {
        this.byEmail = new HashMap<>();
        this.byDomain = new HashMap<>();
    }

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        if (this.byEmail.containsKey(email)) return false;

        Person person = new Person(email, name, age, town);
        String domain = email.substring(email.indexOf('@') + 1);
        this.byEmail.put(email, person);
        this.byDomain.putIfAbsent(domain, new TreeSet<>());
        this.byDomain.get(domain).add(person);
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

        String domain = email.substring(email.indexOf('@') + 1);
        this.byDomain.get(domain).remove(this.byEmail.get(email));
        this.byEmail.remove(email);
        return true;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        return this.byDomain.getOrDefault(emailDomain, Collections.emptySet());
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        throw new UnsupportedOperationException();
    }
}
