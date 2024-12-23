package smartosc.jec.ecommerce.order.service;

import smartosc.jec.ecommerce.order.model.Order;
import java.util.List;

public interface IOrderService {

    void addOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(String orderId);

    List<Order> getAllOrders();

    Order findOrderById(String orderId);
    
}

