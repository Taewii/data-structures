package p01_microsystem.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MicrosystemImpl implements Microsystem {

    private final Map<Integer, Computer> byId;
    private final Map<Brand, List<Integer>> byBrand;

    public MicrosystemImpl() {
        this.byId = new HashMap<>();
        this.byBrand = new HashMap<>();
    }

    @Override
    public void createComputer(Computer computer) {
        if (this.byId.containsKey(computer.getNumber())) {
            throw new IllegalArgumentException();
        }

        this.byId.put(computer.getNumber(), computer);

        this.byBrand.putIfAbsent(computer.getBrand(), new ArrayList<>());
        this.byBrand.get(computer.getBrand()).add(computer.getNumber());
    }

    @Override
    public boolean contains(int number) {
        return this.byId.containsKey(number);
    }

    @Override
    public int count() {
        return this.byId.size();
    }

    @Override
    public Computer getComputer(int number) {
        if (!this.byId.containsKey(number)) {
            throw new IllegalArgumentException();
        }

        return this.byId.get(number);
    }

    @Override
    public void remove(int number) {
        if (!this.byId.containsKey(number)) {
            throw new IllegalArgumentException();
        }

        this.byId.remove(number);
    }

    @Override
    public void removeWithBrand(Brand brand) {
        if (!this.byBrand.containsKey(brand)) {
            throw new IllegalArgumentException();
        }

        this.byBrand.get(brand).forEach(this.byId::remove);
        this.byBrand.remove(brand);
    }

    @Override
    public void upgradeRam(int ram, int number) {
        if (!this.byId.containsKey(number)) {
            throw new IllegalArgumentException();
        }

        Computer computer = this.byId.get(number);
        if (computer.getRAM() < ram) {
            computer.setRAM(ram);
        }
    }

    @Override
    public Iterable<Computer> getAllFromBrand(Brand brand) {
        List<Computer> pcs = new ArrayList<>();
        if (this.byBrand.containsKey(brand)) {
            this.byBrand.get(brand).forEach(id -> pcs.add(this.byId.get(id)));
        }

        return pcs.stream()
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Computer> getAllWithScreenSize(double screenSize) {
        return this.byId.values().stream()
                .filter(pc -> pc.getScreenSize() == screenSize)
                .sorted((a, b) -> b.getNumber() - a.getNumber())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Computer> getAllWithColor(String color) {
        return this.byId.values().stream()
                .filter(pc -> pc.getColor().equals(color))
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Computer> getInRangePrice(double minPrice, double maxPrice) {
        return this.byId.values().stream()
                .filter(pc -> pc.getPrice() >= minPrice && pc.getPrice() <= maxPrice)
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .collect(Collectors.toList());
    }
}
