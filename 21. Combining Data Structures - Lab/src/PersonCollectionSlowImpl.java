import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PersonCollectionSlowImpl implements PersonCollection {

    private Map<String, Person> people;

    public PersonCollectionSlowImpl() {
        this.people = new TreeMap<>();
    }

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        if (this.people.containsKey(email)) return false;
        this.people.put(email, new Person(email, name, age, town));
        return true;
    }

    @Override
    public int getCount() {
        return this.people.size();
    }

    @Override
    public Person findPerson(String email) {
        return this.people.getOrDefault(email, null);
    }

    @Override
    public boolean deletePerson(String email) {
        if (!this.people.containsKey(email)) return false;
        this.people.remove(email);
        return true;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        return this.people.values().stream()
                .filter(p -> p.getEmail().substring(p.getEmail().indexOf("@") + 1).equals(emailDomain))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
       return this.people.values().stream()
               .filter(p -> p.getName().equals(name) && p.getTown().equals(town))
               .collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        return this.people.values().stream()
                .filter(p -> p.getAge() >= startAge && p.getAge() <= endAge)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        return this.people.values().stream()
                .filter(p -> p.getAge() >= startAge && p.getAge() <= endAge && p.getTown().equals(town))
                .collect(Collectors.toList());
    }
}
