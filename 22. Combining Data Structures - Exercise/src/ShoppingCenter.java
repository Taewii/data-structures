import java.util.*;

public class ShoppingCenter {

    //not sure how this is fast enough to work in the tests, but eh, I ain't complainin lmao
    private Map<String, List<Product>> productsByName;
    private Map<String, List<Product>> productsByProducer;
    private Map<String, List<Product>> productsByNameAndProducer;
    private TreeMap<Double, List<Product>> productsByPrice;

    public ShoppingCenter() {
        this.productsByName = new HashMap<>();
        this.productsByProducer = new HashMap<>();
        this.productsByNameAndProducer = new HashMap<>();
        this.productsByPrice = new TreeMap<>();
    }

    public String addProduct(String name, double price, String producer) {
        Product product = new Product(name, price, producer);

        this.productsByName.putIfAbsent(name, new ArrayList<>());
        this.productsByName.get(name).add(product);

        this.productsByProducer.putIfAbsent(producer, new ArrayList<>());
        this.productsByProducer.get(producer).add(product);

        String key = name.concat(producer);
        this.productsByNameAndProducer.putIfAbsent(key, new ArrayList<>());
        this.productsByNameAndProducer.get(key).add(product);

        this.productsByPrice.putIfAbsent(price, new ArrayList<>());
        this.productsByPrice.get(price).add(product);

        return "Product added";
    }

    public String deleteProducts(String producer) {
        if (!this.productsByProducer.containsKey(producer)) {
            return "No products found";
        }

        List<Product> products = this.productsByProducer.get(producer);
        if (producer.isEmpty()) return "No products found";

        for (Product product : products) {
            this.productsByName.get(product.getName()).remove(product);
            this.productsByNameAndProducer.get(product.getName() + product.getProducer()).remove(product);
            this.productsByPrice.get(product.getPrice()).remove(product);
        }

        this.productsByProducer.remove(producer);
        return String.format("%d products deleted", products.size());
    }

    public String deleteProducts(String name, String producer) {
        String key = name.concat(producer);
        if (!this.productsByNameAndProducer.containsKey(key)) {
            return "No products found";
        }

        List<Product> products = this.productsByNameAndProducer.get(key);
        if (products.isEmpty()) return "No products found";

        for (Product product : products) {
            this.productsByName.get(product.getName()).remove(product);
            this.productsByProducer.get(product.getProducer()).remove(product);
            this.productsByPrice.get(product.getPrice()).remove(product);
        }

        this.productsByNameAndProducer.remove(key);
        return String.format("%d products deleted", products.size());
    }

    public List<Product> findProductsByName(String name) {
        return this.productsByName.get(name);
    }

    public List<Product> findProductsByProducer(String producer) {
        return this.productsByProducer.get(producer);
    }

    public List<Product> findProductsByPriceRange(Double from, Double to) {
        List<Product> result = new ArrayList<>();
        this.productsByPrice
                .subMap(from, true, to, true)
                .forEach((key, value) -> result.addAll(value));
        return result;
    }
}