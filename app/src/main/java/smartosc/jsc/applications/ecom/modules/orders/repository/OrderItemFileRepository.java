package smartosc.jsc.applications.ecom.modules.orders.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.ecom.modules.orders.model.OrderItem;
import smartosc.jsc.applications.ecom.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderItemFileRepository implements OrderItemRepositoryInterface {

    private static final String ORDER_ITEM_FILE = OrderItem.TABLE_NAME + ".json";

    protected List<OrderItem> allOrderItems;

    @Override
    public void save(OrderItem orderItem) throws IOException {
        List<OrderItem> allOrderItems = this.getAll();
        Map<String, OrderItem> allOrderItemsMap;
        allOrderItemsMap = this.getAllOrderItemsMap(allOrderItems);
        int lastKey = 1;

        if (allOrderItemsMap == null) {
            allOrderItemsMap = new HashMap<>();
        }

        if (orderItem.getId() == null && allOrderItemsMap.keySet().toArray().length > 1) {
            lastKey = Integer.parseInt(allOrderItemsMap.keySet().toArray()[allOrderItemsMap.size() - 1].toString());
        }
        orderItem.setId(String.valueOf(lastKey + 1));

        allOrderItemsMap.put(orderItem.getId(), orderItem);
        allOrderItems = this.getAllOrderItemsList(allOrderItemsMap);
        ObjectMapper objectMapper = new ObjectMapper();
        FileUtils.writeToFile(ORDER_ITEM_FILE, objectMapper.writeValueAsString(allOrderItems));
    }

    @Override
    public List<OrderItem> getAll() throws IOException {
        if (this.allOrderItems == null) {
            String orderItemJsonContent = FileUtils.readFromFile(ORDER_ITEM_FILE);
            ObjectMapper objectMapper = new ObjectMapper();
            if (orderItemJsonContent.isEmpty()) {
                this.allOrderItems = new ArrayList<>();
            } else {
                this.allOrderItems = objectMapper.readValue(orderItemJsonContent, new TypeReference<>() {
                });
            }
        }
        return this.allOrderItems;
    }

    private Map<String, OrderItem> getAllOrderItemsMap(List<OrderItem> allOrders) {
        return allOrders.stream()
                .collect(Collectors.toMap(OrderItem::getId, orderItem -> orderItem));
    }

    private List<OrderItem> getAllOrderItemsList(Map<String, OrderItem> allOrderItemsMap) {
        return new ArrayList<>(allOrderItemsMap.values());
    }
}
