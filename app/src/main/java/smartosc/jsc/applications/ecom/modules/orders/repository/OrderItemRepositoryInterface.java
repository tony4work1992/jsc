package smartosc.jsc.applications.ecom.modules.orders.repository;

import smartosc.jsc.applications.ecom.modules.orders.model.Order;
import smartosc.jsc.applications.ecom.modules.orders.model.OrderItem;

import java.io.IOException;
import java.util.List;

public interface OrderItemRepositoryInterface {
    void save(OrderItem orderItem) throws IOException;
    List<OrderItem> getAll() throws IOException;
}
