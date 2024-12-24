package smartosc.jsc.applications.ecom.mo_nodes.order.model;

import lombok.Data;
import smartosc.jsc.applications.ecom.mo_nodes.product.model.Product;

import java.util.List;

@Data
public class Order {
    private Integer orderId;
    private String orderStatus;
    private Integer quantity;
    private double amount;
    private Integer customerId;
    private List<Product> products;

    public Order(Integer orderId, String orderStatus, Integer quantity, double amount, Integer customerId) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.quantity = quantity;
        this.amount = amount;
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return orderId + "," + orderStatus + "," + quantity + "," + amount;
    }
}
