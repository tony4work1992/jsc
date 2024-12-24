package smartosc.jec.applications.ecommerce.order.services;

import java.util.List;

import smartosc.jec.applications.ecommerce.order.model.Order;

public interface OrderServiceInterface {
    void addOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(String orderId);

    List<Order> getAllOrders();

    Order findOrderById(String orderId);
}
