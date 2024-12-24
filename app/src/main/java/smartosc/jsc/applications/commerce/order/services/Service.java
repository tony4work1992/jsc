package smartosc.jsc.applications.commerce.order.services;

import java.util.List;

import smartosc.jsc.applications.commerce.order.model.Order;
import smartosc.jsc.applications.commerce.product.model.Product;

public class Service {
    public Order createOrder(List<Integer> productIds, Integer userId) {
        if (productIds == null || productIds.isEmpty()) {
            throw new IllegalArgumentException("Product list cannot be null or empty");
        }
        Order order = new Order();
        List<Product> products = productIds.stream().map((productId) -> new Product(productId)).toList();
        order.setProducts(products);
        double amount = products.stream().mapToDouble(Product::getPrice).sum();
        order.setQuantity(products.size());
        order.setAmount(amount);
        order.setUserId(userId);
        return order;
    }

    public String refundOrder(Integer orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        System.out.println("Processing refund for order ID: " + orderId);
        return "Refund ID: REF" + orderId;
    }

    public String refundOrders(List<Integer> orderIds) {
        if (orderIds == null || orderIds.isEmpty()) {
            throw new IllegalArgumentException("Order list cannot be null or empty");
        }
        System.out.println("Processing refunds for order IDs: " + orderIds);
        return "Batch Refund IDs processed for: " + orderIds;
    }

    public void cancelOrders(List<Integer> orderIds) {
        if (orderIds == null || orderIds.isEmpty()) {
            throw new IllegalArgumentException("Order list cannot be null or empty");
        }
        System.out.println("Cancelling orders: " + orderIds);
        // Example: Update orders to canceled state
    }

    public void cancelOrder(Integer orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        System.out.println("Cancelling order ID: " + orderId);
        // Example: Update order to canceled state
    }
}
