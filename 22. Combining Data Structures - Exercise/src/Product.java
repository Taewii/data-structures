public class Product implements Comparable<Product> {
    private String name;
    private Double price;
    private String producer;

    public Product(String name, Double price, String producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getProducer() {
        return this.producer;
    }

    @Override
    public int compareTo(Product other) {
        int result = this.name.compareTo(other.getName());

        if (result == 0) {
            result = this.producer.compareTo(other.getProducer());
        }

        if (result == 0) {
            result = Double.compare(this.price, other.getPrice());
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("{%s;%s;%.2f}", this.name, this.producer, this.price);
    }
}
