package services;

import dtos.OrderDto;
import entitites.Order;
import enums.OrderStatus;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IOrderService {
    public Order createOrder(OrderDto order) throws IOException;

    public List<Order> loadOrders();

    public Optional<Order> findOrderById(String id) ;

    Order updateStatusOrder(Long orderId, OrderStatus orderStatus) throws IOException;
}
