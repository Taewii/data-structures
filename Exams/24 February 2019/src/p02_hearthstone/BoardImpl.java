package p02_hearthstone;

import java.util.*;
import java.util.stream.Collectors;

public class BoardImpl implements Board {

    private final Map<String, Card> cards;
    private final Set<String> deadCards;

    public BoardImpl() {
        this.cards = new HashMap<>();
        this.deadCards = new HashSet<>();
    }

    @Override
    public void draw(Card card) {
        if (this.cards.containsKey(card.getName())) {
            throw new IllegalArgumentException();
        }

        this.cards.put(card.getName(), card);
    }

    @Override
    public Boolean contains(String name) {
        return this.cards.containsKey(name);
    }

    @Override
    public int count() {
        return this.cards.size();
    }

    @Override
    public void play(String attackerCardName, String attackedCardName) {
        Card attacker = this.cards.get(attackerCardName);
        Card attacked = this.cards.get(attackedCardName);

        if (attacker == null || attacked == null || (attacker.getLevel() != attacked.getLevel())) {
            throw new IllegalArgumentException();
        }

        boolean isDead = attacker.attack(attacked);
        if (isDead) {
            this.deadCards.add(attackedCardName);
        }
    }

    @Override
    public void remove(String name) {
        if (!this.cards.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        this.cards.remove(name);
    }

    @Override
    public void removeDeath() {
        this.deadCards.forEach(this.cards::remove);
    }

    @Override
    public Iterable<Card> getBestInRange(int start, int end) {
        return this.cards.values().stream()
                .filter(c -> c.getScore() >= start && c.getScore() <= end)
                .sorted((a, b) -> Integer.compare(b.getLevel(), a.getLevel()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Card> listCardsByPrefix(String prefix) {
        return this.cards.values().stream()
                .filter(c -> c.getName().startsWith(prefix))
                .sorted((a, b) -> {
                    String reversedA = new StringBuilder(a.getName()).reverse().toString();
                    String reversedB = new StringBuilder(b.getName()).reverse().toString();
                    if (reversedA.compareTo(reversedB) == 0) {
                        return a.getLevel() - b.getLevel();
                    }
                    return reversedA.compareTo(reversedB);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Card> searchByLevel(int level) {
        return this.cards.values().stream().filter(c -> c.getLevel() == level)
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                .collect(Collectors.toList());
    }

    @Override
    public void heal(int health) {
        Card card = this.cards.values().stream().min(Comparator.comparing(Card::getHealth)).orElse(null);
        if (card != null) {
            card.setHealth(card.getHealth() + health);
            if (card.getHealth() > 0) {
                this.deadCards.remove(card.getName());
            }
        }
    }
}
