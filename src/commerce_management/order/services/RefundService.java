package commerce_management.order.services;

import java.util.List;

public class RefundService {
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
}
