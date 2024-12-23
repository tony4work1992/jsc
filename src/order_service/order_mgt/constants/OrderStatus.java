package order_service.order_mgt.constants;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderStatus {
    public static final String ORDER_STATUS_PENDING = "Pending";
    public static final String ORDER_STATUS_SHIPPED = "Shipped";
    public static final String ORDER_STATUS_DELIVERED = "Delivered";
    public static final List<String> ORDER_ALLOW_STATUS = Arrays.asList(ORDER_STATUS_PENDING, ORDER_STATUS_SHIPPED, ORDER_STATUS_DELIVERED);
    public static final Map<String, Integer> LEVEL_STATUS = Map.of(
            ORDER_STATUS_PENDING, 1,
            ORDER_STATUS_SHIPPED, 2,
            ORDER_STATUS_DELIVERED, 3
        );
}
