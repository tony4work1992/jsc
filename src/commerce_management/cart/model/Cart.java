package commerce_management.cart.model;
import java.util.List;

import commerce_management.product.model.Product;

public class Cart {
    private List<Product> products;
    private double amount;
    private Integer userId;

    public Cart(List<Product> products, double amount, Integer userId) {
        this.products = products;
        this.amount = amount;
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
