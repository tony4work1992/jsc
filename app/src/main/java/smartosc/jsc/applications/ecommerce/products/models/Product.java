package smartosc.jsc.applications.ecommerce.products.models;

public class Product {
    private final int id;
    private String name;
    private String sku;
    private double price;
    private int stock;

    public Product(int id, String sku, String name, double price, int stock) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", SKU: " + sku + ", Name: " + name + ", Price: $" + price + ", Stock: " + stock;
    }

    public String toCsv() {
        return id + "," + sku + "," + name + "," + price + "," + stock;
    }
}
