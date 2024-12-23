package order_service.order_mgt.repository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import order_service.order_mgt.model.Order;

public class OrderRepository {
    private static final String ORDER_FILE = System.getProperty("user.dir") + "/src/source/order.txt";
    private Map<Integer, Order> orders = new HashMap<>();
    private boolean isLoad = false;

    public void loadOrders() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                List<Integer> productIds = new ArrayList<>();
                for (String part : parts[1].split("_")) {
                    productIds.add(Integer.parseInt(part)); // Parse each part into Integer
                }
                List<Integer> promotionIds = new ArrayList<>();
                for (String part : parts[2].split("_")) {
                    if(!part.isEmpty()) {
                        promotionIds.add(Integer.parseInt(part)); // Parse each part into Integer
                    }
                }
                String customerEmail = parts[3];
                double price = Double.parseDouble(parts[4]);
                String status = parts[5];
                orders.put(id, new Order(id, productIds, promotionIds, customerEmail, price, status));
            }
            isLoad = true;
        } catch (IOException e) {
            System.out.println("Error loading orders: " + e.getMessage());
        }
    }

    public Map<Integer, Order> getListOrder() {
        if(!isLoad) {
            loadOrders();
        }
        return orders;
    }

    public Order getOrderById(int orderId) {
        if(!isLoad) {
            loadOrders();
        }
        if(!orders.containsKey(orderId)) {
            throw new NoSuchElementException("Order with ID " + orderId + " does not exist.");
        }

        return orders.get(orderId);
    }

    public int getLastRealOrderId()
    {
        if(!isLoad) {
            loadOrders();
        }
        return orders.isEmpty() ? 1 : orders.get(orders.size()).getId();
    }

    public void saveOrder(Order order){
        File file = new File(ORDER_FILE);
        try (
                FileWriter fw = new FileWriter(file, true); // 'true' enables append mode
                BufferedWriter writer = new BufferedWriter(fw)
        ){
            if (file.exists() && file.length() > 0) {
                writer.write("\n");
            }
            String productIds = order.getProductIds().stream()
                    .map(String::valueOf) // Convert each Integer to String
                    .collect(Collectors.joining("_"));
            String promotionIds = order.getPromotionIds().stream()
                    .map(String::valueOf) // Convert each Integer to String
                    .collect(Collectors.joining("_"));

            writer.write(order.getId() + "," + productIds + "," + promotionIds + "," + order.getCustomerEmail() + "," + order.getTotalPrice() + "," + order.getStatus());
        } catch (IOException e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
        isLoad = false;

        System.out.println("Order placed successfully! Order ID: " + order.getId());
    }

    public void updateOrder(int orderId, Order order) {
        File file = new File(ORDER_FILE);

        // Read all lines from the file
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                if(id == orderId) {
                    String productIds = order.getProductIds().stream()
                            .map(String::valueOf) // Convert each Integer to String
                            .collect(Collectors.joining("_"));
                    String promotionIds = order.getPromotionIds().stream()
                            .map(String::valueOf) // Convert each Integer to String
                            .collect(Collectors.joining("_"));
                    line = order.getId() + "," + productIds + "," + promotionIds + "," + order.getCustomerEmail() + "," + order.getTotalPrice() + "," + order.getStatus();
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading lines orders: " + e.getMessage());
        }

        // Write the updated lines back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error updated lines orders: " + e.getMessage());
        }
        isLoad = false;
        System.out.println("Order updated successfully!");
    }
}
