package product_service.product_mgt.model;


public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stockQty;

    // Constructor
    public Product(int id, String name, String description, double price, int stockQty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQty = stockQty;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }
}
