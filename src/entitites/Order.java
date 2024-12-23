package entitites;

import enums.OrderStatus;

import java.io.Serializable;

public class Order implements Serializable {
    private Long id;
    private String customerName;
    private Long promotionId;
    private double totalPrice;
    private OrderStatus status; // Pending, Shipped, Delivered

    public Order() {

    }

    // Constructors, getters, and setters
    public Order(Long id, String customerName,  Long promotionId, double totalPrice, OrderStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.promotionId = promotionId;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Order ID: %s, Customer: %s, Total Price: %.2f, Status: %s", id, customerName, totalPrice, status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
// Getters and setters omitted for brevity
}