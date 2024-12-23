package commerce_management.order.model;

import java.util.Arrays;
import java.util.List;
import commerce_management.product.model.Product;
import commerce_management.promotion.model.Promotion;

public class Order {
    private static int orderCounter = 1;
    private int orderId;
    private int customerId;
    private List<Integer> productIds;
    private int promotionId;
    private String status;
    private double totalPrice;

    public Order(int customerId, List<Product> products, double totalPrice, int promotionId) {
        this.orderId = orderCounter++;
        this.customerId = customerId;
        this.productIds = products.stream().map(Product::getProductId).toList();
        this.promotionId = promotionId;
        this.status = "Pending";
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return orderId + "," + customerId + "," + productIds + "," + totalPrice + "," + promotionId + "," + status;
    }
}
