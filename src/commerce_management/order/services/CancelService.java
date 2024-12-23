package commerce_management.order.services;

import java.util.List;

public class CancelService {
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
