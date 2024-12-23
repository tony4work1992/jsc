package commerce_management.product.model;

public class Product {
    private Integer productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    public Product(int productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Product setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String desciption) {
        this.description = desciption;
        return this;

    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public Product setStockQuantity(int quantity) {
        this.stockQuantity = quantity;
        return this;
    }

}
