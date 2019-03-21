package p01_olympics;

import java.util.*;
import java.util.stream.Collectors;

public class OlympicsImpl implements Olympics {

    private final Map<Integer, Competitor> competitors;
    private final Map<Integer, Competition> competitions;
    private final SortedMap<String, SortedSet<Integer>> nameAndIds;

    public OlympicsImpl() {
        this.competitions = new HashMap<>();
        this.competitors = new HashMap<>();
        this.nameAndIds = new TreeMap<>();
    }

    @Override
    public void addCompetitor(int id, String name) {
        if (this.competitors.containsKey(id)) {
            throw new IllegalArgumentException();
        }

        this.competitors.put(id, new Competitor(id, name));
        this.nameAndIds.putIfAbsent(name, new TreeSet<>());
        this.nameAndIds.get(name).add(id);
    }

    @Override
    public void addCompetition(int id, String name, int score) {
        if (this.competitions.containsKey(id)) {
            throw new IllegalArgumentException();
        }

        this.competitions.put(id, new Competition(name, id, score));
    }

    @Override
    public void compete(int competitorId, int competitionId) {
        if (!this.competitions.containsKey(competitionId) || !this.competitors.containsKey(competitorId)) {
            throw new IllegalArgumentException();
        }

        this.competitions.get(competitionId).addCompetitor(this.competitors.get(competitorId));
    }

    @Override
    public void disqualify(int competitionId, int competitorId) {

        if (!this.competitions.containsKey(competitionId) || !this.competitors.containsKey(competitorId)) {
            throw new IllegalArgumentException();
        }

        boolean isSuccess = this.competitions.get(competitionId).disqualify(this.competitors.get(competitorId));

        if (!isSuccess) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Iterable<Competitor> findCompetitorsInRange(long min, long max) {
        return this.competitors.values()
                .stream()
                .filter(c -> c.getTotalScore() > min && c.getTotalScore() <= max)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Competitor> getByName(String name) {
        if (!this.nameAndIds.containsKey(name)) {
            throw new IllegalArgumentException();
        }

        SortedSet<Integer> integers = this.nameAndIds.get(name);
        List<Competitor> res = new ArrayList<>(integers.size());

        for (Integer integer : integers) {
            res.add(this.competitors.get(integer));
        }
        return res;
    }

    @Override
    public Iterable<Competitor> searchWithNameLength(int minLength, int maxLength) {
        Set<String> filter = this.nameAndIds.keySet().stream()
                .filter(n -> n.length() >= minLength && n.length() <= maxLength)
                .collect(Collectors.toSet());

        if (filter.isEmpty()) {
            return Collections.emptyList();
        }

        List<Competitor> res = new ArrayList<>(filter.size());
        for (String s : filter) {
            SortedSet<Integer> integers = this.nameAndIds.get(s);

            for (Integer integer : integers) {
                res.add(this.competitors.get(integer));
            }
        }
        res.sort(Comparator.comparingInt(Competitor::getId));
        return res;
    }

    @Override
    public Boolean contains(int competitionId, Competitor comp) {
        if (!this.competitions.containsKey(competitionId)) {
            throw new IllegalArgumentException();
        }

        return this.competitions.get(competitionId).getCompetitors().contains(comp);
    }

    @Override
    public int competitionsCount() {
        return this.competitions.size();
    }

    @Override
    public int competitorsCount() {
        return this.competitors.size();
    }

    @Override
    public Competition getCompetition(int id) {
        if (!this.competitions.containsKey(id)) {
            throw new IllegalArgumentException();
        }

        return this.competitions.get(id);
    }
}
