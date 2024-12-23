package order_service.order_mgt.model;

import java.util.List;
import order_service.order_mgt.constants.OrderStatus;

public class Order {
    private int id;
    private List<Integer> productIds;
    private List<Integer> promotionIds;
    private String customerEmail;
    private double totalPrice;
    private String status;

    public Order(int id, List<Integer> productIds, List<Integer> promotionIds, String customerEmail, double totalPrice, String status) {
        this.id = id;
        this.productIds = productIds;
        this.promotionIds = promotionIds;
        this.customerEmail = customerEmail;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order(int id, List<Integer> productIds, List<Integer> promotionIds, String customerEmail, double totalPrice) {
        this.id = id;
        this.productIds = productIds;
        this.promotionIds = promotionIds;
        this.customerEmail = customerEmail;
        this.totalPrice = totalPrice;
        this.status = OrderStatus.ORDER_STATUS_PENDING;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for productIds
    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    // Getter and Setter for promotionIds
    public List<Integer> getPromotionIds() {
        return promotionIds;
    }

    public void setPromotionIds(List<Integer> promotionIds) {
        this.promotionIds = promotionIds;
    }

    // Getter and Setter for customerEmail
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    // Getter and Setter for totalPrice
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
