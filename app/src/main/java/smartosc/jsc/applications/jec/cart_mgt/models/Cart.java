package smartosc.jsc.applications.jec.cart_mgt.models;

import java.util.List;

import lombok.Data;
import smartosc.jsc.applications.jec.product_mgt.models.Product;

@Data
public class Cart {
    private List<Product> products;
    private double amount;
    private Integer userId;
    private List<Integer> promotionIds;

    public Cart(List<Product> products, double amount, Integer userId) {
        this.products = products;
        this.amount = amount;
        this.userId = userId;
    }
}