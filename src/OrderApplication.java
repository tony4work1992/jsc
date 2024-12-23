import dtos.OrderDto;
import entitites.Order;
import enums.OrderStatus;
import services.IOrderService;
import services.IProductService;
import services.IPromotionService;
import services.impls.OrderServiceImpl;
import services.impls.ProductServiceImpl;
import services.impls.PromotionServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class OrderApplication {
    public static void main(String[] args) throws IOException {
        // Tạo đối tượng IOrderService để sử dụng các phương thức
        IPromotionService promotionService = new PromotionServiceImpl();
        IProductService productService = new ProductServiceImpl();
        IOrderService orderService = new OrderServiceImpl(promotionService,productService);

        // 1. Tạo đơn hàng mới
        OrderDto newOrderDto = new OrderDto();
        newOrderDto.setCustomerName("John Doe");
        newOrderDto.setCustomerContact("123456789");
        newOrderDto.setPromotionId(1L);
        newOrderDto.setTotalPrice(150.0);
        newOrderDto.setProductIds(List.of(2L));
        newOrderDto.setStatus(OrderStatus.PENDING); // Trạng thái mặc định là PENDING

        Order newOrder = orderService.createOrder(newOrderDto);
        System.out.println("Order created: " + newOrder);

        // 2. Tải danh sách đơn hàng
        List<Order> orders = orderService.loadOrders();
        System.out.println("Loaded Orders: ");
        for (Order order : orders) {
            System.out.println(order);
        }

        // 3. Tìm đơn hàng theo ID
        String orderIdToFind = newOrder.getId().toString();
        Optional<Order> foundOrder = orderService.findOrderById(orderIdToFind);
        foundOrder.ifPresent(order -> System.out.println("Found Order: " + order));

        // 4. Cập nhật trạng thái đơn hàng
        OrderStatus newStatus = OrderStatus.SHIPPED;
        Order updatedOrder = orderService.updateStatusOrder(newOrder.getId(), newStatus);
        if (updatedOrder != null) {
            System.out.println("Updated Order Status: " + updatedOrder);
        } else {
            System.out.println("Order not found or update failed.");
        }
    }
}
