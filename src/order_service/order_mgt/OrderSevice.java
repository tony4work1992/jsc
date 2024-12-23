package order_service.order_mgt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import order_service.order_mgt.constants.OrderStatus;
import order_service.order_mgt.model.Order;
import order_service.order_mgt.repository.OrderRepository;
import product_service.product_mgt.ProductSevice;
import promotion_service.promotion_mgt.PromotionService;

public class OrderSevice {

    OrderRepository orderRepository = new OrderRepository();
    ProductSevice productSevice = new ProductSevice();
    PromotionService promotionService = new PromotionService();

    public void viewOrder(Scanner scanner) {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        Order order = orderRepository.getOrderById(orderId);
        StringBuilder sb = new StringBuilder();
        System.out.println("\n--- Order Info ---");
        sb.append("Customer Email: ").append(order.getCustomerEmail()).append("\n");
        sb.append("Products:\n");
        for (int productId : order.getProductIds()) {
            sb.append("\t").append(productSevice.getProductNameById(productId)).append("\n");
        }
        sb.append("\n");
        sb.append("Promotions:\n");
        for (int promotionId : order.getPromotionIds()) {
            sb.append("\t").append(promotionService.getPromotionNameById(promotionId)).append("\n");
        }
        sb.append("\n");
        sb.append("Total Price: $").append(order.getTotalPrice()).append("\n");
        sb.append("Status: ").append(order.getStatus()).append("\n");
        System.out.println(sb.toString());
    }

    public void updateOrderStatus(Scanner scanner) {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        Order order = orderRepository.getOrderById(orderId);
        System.out.print("Enter new status (Pending/Shipped/Delivered): ");
        scanner.nextLine(); // Consume newline
        while (true) {
            String status = scanner.nextLine();
            if (status.isEmpty()) break;
            if(OrderStatus.ORDER_ALLOW_STATUS.contains(status)) {
                // update Status when level updated Status >=  current Status
                if(OrderStatus.LEVEL_STATUS.get(status) >= OrderStatus.LEVEL_STATUS.get(order.getStatus())) {
                    order.setStatus(status);
                    orderRepository.updateOrder(order.getId(), order);
                    System.out.println("Order status updated successfully!");
                    break;
                } else {
                    System.out.println("Cannot update to "+status+". Try again!");
                }
            } else {
                System.out.println("Wrong order status. Try again!");
            }
        }
    }

    public void placeOrder(Scanner scanner) {
        List<Integer> orderProductIds = new ArrayList<>();
        double total = 0;

        System.out.print("Enter Customer Email: ");
        scanner.nextLine(); // Consume newline
        String customerEmail = scanner.nextLine();
        while (true) {
            System.out.print("Enter Product ID to add to order (or 0 to finish): ");
            int productId = scanner.nextInt();
            if (productId == 0) break;
            double productAmount = productSevice.addProductToCart(productId);
            if(productAmount > 0)
            {
                orderProductIds.add(productId);
                total+= productAmount;
            }
        }

         System.out.print("Apply promotion? (yes/no): ");
         scanner.nextLine(); // Consume newline
         String applyPromo = scanner.nextLine();
         List<Integer> orderPromtionIds = new ArrayList<>();
         if (applyPromo.equalsIgnoreCase("yes")) {
             promotionService.viewPromotions();
             while (true) {
                 System.out.print("Enter Promotion ID apply (or 0 to finish): ");
                 int promotionId = scanner.nextInt();
                 if (promotionId == 0) break;
                 if (promotionService.isApplicable(promotionId, total)) {
                     total = promotionService.apply(promotionId, total);
                     System.out.println("Promotion applied successfully!");
                     orderPromtionIds.add(promotionId);
                 } else {
                     System.out.println("Promotion not applicable.");
                 }
             }
         }

        Order order = new Order(orderRepository.getLastRealOrderId() + 1, orderProductIds, orderPromtionIds, customerEmail, total);
        orderRepository.saveOrder(order);
    }
}
