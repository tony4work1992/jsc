package smartosc.jsc.applications.jec.order_mgt.services;

import java.util.List;
import java.util.Random;

import smartosc.jsc.applications.jec.cart_mgt.models.Cart;
import smartosc.jsc.applications.jec.order_mgt.models.Order;
import smartosc.jsc.applications.jec.product_mgt.models.Product;

public class OrderService {

    public Order createOrder(Cart cart) {
        List<Product> products = cart.getProducts();
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Product list cannot be null or empty");
        }
        Order order = new Order();
        Random rand = new Random();
        order.setOrderId(rand.nextInt(1000));
        order.setProducts(products);
        order.setAmount(cart.getAmount());
        order.setUserId(cart.getUserId());
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
