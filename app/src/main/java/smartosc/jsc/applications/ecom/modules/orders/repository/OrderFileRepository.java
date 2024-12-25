package smartosc.jsc.applications.ecom.modules.orders.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.ecom.modules.orders.model.Order;
import smartosc.jsc.applications.ecom.modules.products.model.Product;
import smartosc.jsc.applications.ecom.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderFileRepository implements OrderRepositoryInterface {

    private static final String ORDER_FILE = Order.TABLE_NAME + ".json";

    protected List<Order> allOrders;

    @Override
    public void save(Order order) throws IOException {
        List<Order> allOrders = this.getAll();
        Map<String, Order> allOrdersMap;
        allOrdersMap = this.getAllOrdersMap(allOrders);
        int lastKey = 1;
        if (allOrdersMap == null) {
            allOrdersMap = new HashMap<>();
        }

        if (order.getId() == null && allOrdersMap.keySet().toArray().length > 1) {
            lastKey = Integer.parseInt(allOrdersMap.keySet().toArray()[allOrdersMap.size() - 1].toString());
        }
        order.setId(String.valueOf(lastKey + 1));

        allOrdersMap.put(order.getId(), order);
        allOrders = this.getAllOrderList(allOrdersMap);
        ObjectMapper objectMapper = new ObjectMapper();
        FileUtils.writeToFile(ORDER_FILE, objectMapper.writeValueAsString(allOrders));
    }

    @Override
    public List<Order> getAll() throws IOException {
        if (this.allOrders == null) {
            String orderJsonContent = FileUtils.readFromFile(ORDER_FILE);
            ObjectMapper objectMapper = new ObjectMapper();
            if (orderJsonContent.isEmpty()) {
                this.allOrders = new ArrayList<>();
            } else {
                this.allOrders = objectMapper.readValue(orderJsonContent, new TypeReference<>() {
                });
            }
        }
        return this.allOrders;
    }

    private Map<String, Order> getAllOrdersMap(List<Order> allOrders) {
        return allOrders.stream()
                .collect(Collectors.toMap(Order::getId, order -> order));
    }

    private List<Order> getAllOrderList(Map<String, Order> allOrdersMap) {
        return new ArrayList<>(allOrdersMap.values());
    }
}
