package instock;

import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private Map<String, Product> products;
    private List<Product> byInsertion;
    private Map<Integer, List<Product>> byQuantity;

    public Instock() {
        this.products = new TreeMap<>();
        this.byInsertion = new ArrayList<>();
        this.byQuantity = new HashMap<>();
    }

    @Override
    public int getCount() {
        return this.byInsertion.size();
    }

    @Override
    public boolean contains(Product product) {
        return this.products.containsKey(product.getLabel());
    }

    @Override
    public void add(Product product) {
        this.products.put(product.getLabel(), product);
        this.byInsertion.add(product);

        this.byQuantity.putIfAbsent(product.getQuantity(), new ArrayList<>());
        this.byQuantity.get(product.getQuantity()).add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        if (!this.products.containsKey(product)) {
            throw new IllegalArgumentException();
        }

        Product prd = this.products.get(product);
        this.byQuantity.get(prd.getQuantity()).remove(prd);
        prd.setQuantity(quantity);
        this.byQuantity.putIfAbsent(quantity, new ArrayList<>());
        this.byQuantity.get(quantity).add(prd);
    }

    @Override
    public Product find(int index) {
        if (index < 0 || index >= this.byInsertion.size()) {
            throw new IndexOutOfBoundsException();
        }

        return this.byInsertion.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        if (!this.products.containsKey(label)) {
            throw new IllegalArgumentException();
        }

        return this.products.get(label);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        List<Product> products = this.products.values().stream()
                .limit(count)
                .collect(Collectors.toList());

        if (products.size() < count) {
            throw new IllegalArgumentException();
        }


        return products;
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return this.byInsertion.stream()
                .filter(p -> p.getPrice() > lo && p.getPrice() <= hi)
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.byInsertion.stream()
                .filter(p -> p.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (this.products.size() < count) {
            throw new IllegalArgumentException();
        }

        return this.byInsertion.stream()
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.byQuantity.getOrDefault(quantity, Collections.emptyList());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.byInsertion.iterator();
    }
}
