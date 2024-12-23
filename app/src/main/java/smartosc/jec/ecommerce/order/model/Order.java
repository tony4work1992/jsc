package smartosc.jec.ecommerce.order.model;

import java.util.List;

public class Order {
    private String orderId;
    private List<String> products;
    private double totalPrice;
    private String status;

    public Order(String orderId, List<String> products, double totalPrice, String status) {
        this.orderId = orderId;
        this.products = products;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
