package smartosc.jsc.applications.ecom.modules.orders.repository;

import smartosc.jsc.applications.ecom.modules.orders.model.Order;

import java.io.IOException;
import java.util.List;

public interface OrderRepositoryInterface {
    void save(Order order) throws IOException;
    List<Order> getAll() throws IOException;
}
