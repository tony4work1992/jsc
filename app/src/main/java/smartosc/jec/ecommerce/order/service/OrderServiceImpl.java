package smartosc.jec.ecommerce.order.service;

import smartosc.jec.ecommerce.order.model.Order;
import smartosc.jec.ecommerce.util.CSVUtil;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements IOrderService {

    private static final String ORDER_FILE = "app/src/main/java/smartosc/jec/ecommerce/data/orders.csv";

    @Override
    public void addOrder(Order order) {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{order.getOrderId(), String.join(",", order.getProducts()), String.valueOf(order.getTotalPrice()), order.getStatus()});
        CSVUtil.writeCSV(ORDER_FILE, data, true);
    }

    @Override
    public void updateOrder(Order order) {
        List<Order> orders = getAllOrders();
        for (Order o : orders) {
            if (o.getOrderId().equals(order.getOrderId())) {
                o.setProducts(order.getProducts());
                o.setTotalPrice(order.getTotalPrice());
                o.setStatus(order.getStatus());
                break;
            }
        }
        saveAllOrders(orders);
    }

    @Override
    public void deleteOrder(String orderId) {
        List<Order> orders = getAllOrders();
        orders.removeIf(o -> o.getOrderId().equals(orderId));
        saveAllOrders(orders);
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        List<String[]> data = CSVUtil.readCSV(ORDER_FILE);
        for (String[] record : data) {
            List<String> products = List.of(record[1].split(","));
            Order order = new Order(record[0], products, Double.parseDouble(record[2]), record[3]);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public Order findOrderById(String orderId) {
        for (Order order : getAllOrders()) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    private void saveAllOrders(List<Order> orders) {
        List<String[]> data = new ArrayList<>();
        for (Order order : orders) {
            data.add(new String[]{order.getOrderId(), String.join(",", order.getProducts()), String.valueOf(order.getTotalPrice()), order.getStatus()});
        }
        CSVUtil.writeCSV(ORDER_FILE, data, false);
    }
}
