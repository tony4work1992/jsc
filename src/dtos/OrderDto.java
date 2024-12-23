package dtos;

import enums.OrderStatus;

import java.io.Serializable;
import java.util.List;

public class OrderDto  implements Serializable {
    private Long id;
    private String customerName;
    private String customerContact;
    private Long promotionId;
    private double totalPrice;
    private OrderStatus status; // Pending, Shipped, Delivered
    private String promotionCode;
    private List<Long> productIds;
    
    public List<Long> getProductIds() {
        return productIds;
    }
    
    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
    

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }
    public OrderDto(){

    }
    public OrderDto(Long id, String customerName, String customerContact, Long promotionId, double totalPrice, OrderStatus status, String promotionCode) {
        this.id = id;
        this.customerName = customerName;
        this.customerContact = customerContact;
        this.promotionId = promotionId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.promotionCode = promotionCode;
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

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
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