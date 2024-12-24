package smartosc.jsc.applications.ecom.mo_nodes.order;

import smartosc.jsc.applications.ecom.ba_nodes.FileManager;
import smartosc.jsc.applications.ecom.mo_nodes.cart.model.Cart;
import smartosc.jsc.applications.ecom.mo_nodes.customer.model.Customer;
import smartosc.jsc.applications.ecom.mo_nodes.order.constant.OrderStatus;
import smartosc.jsc.applications.ecom.mo_nodes.order.model.Order;
import smartosc.jsc.applications.ecom.mo_nodes.product.ProductManagement;
import smartosc.jsc.applications.ecom.mo_nodes.product.model.Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderManagement {
    String fileName = "orders.txt";

    public Order placeOrder(Cart cart, Customer customer) {
        Integer highestOrderId = getHighestOrderId();
        int newOrderId = highestOrderId == 0 ? 1 : highestOrderId + 1;

        Order order = new Order(
                newOrderId,
                OrderStatus.PENDING,
                cart.getQty(),
                cart.getTotal(),
                customer.getId()
        );

        order.setProducts(cart.getProducts());
        saveOrderToFile(Arrays.asList(order), true);

        return order;
    }

    public Order updateOrderStatus(Integer orderId, String status) {
        Order order = getOrderById(orderId);
        order.setOrderStatus(status);

        List<Order> orders = getAllOrders();
        orders.removeIf(o -> o.getOrderId().equals(orderId));
        orders.add(order);

        saveOrderToFile(orders, false);

        return order;
    }

    public List<Order> getAllOrders() {
        String row;
        List<Order> orders = new ArrayList<>();
        FileManager fileManager = new FileManager();
        BufferedReader fileReader = fileManager.getFileReader(fileName);

        try {
            while ((row = fileReader.readLine()) != null) {
                String[] parts = row.split(",");

                if (parts.length == 5) {
                    Order order = new Order(
                        Integer.parseInt(parts[0].trim()),
                        parts[1].trim(),
                        Integer.parseInt(parts[2].trim()),
                        Double.parseDouble(parts[3].trim()),
                        Integer.parseInt(parts[4].trim())
                    );

                    order.setProducts(getProducts(parts[5].trim()));
                    orders.add(order);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    public List<Product> getProducts(String productRow) {
        List<Product> products = new ArrayList<>();
        List<String> rows = Arrays.asList(productRow.split(","));

        for (String row : rows) {
            String[] parts = row.split(",");
            Product product = new Product(
                    Integer.parseInt(parts[0].trim()),
                    parts[1].trim(),
                    parts[2].trim(),
                    Double.parseDouble(parts[3].trim()),
                    Integer.parseInt(parts[4].trim())
            );

            products.add(product);
        }

        return products;
    }

    public Integer getHighestOrderId() {
        Integer maxId = 0;

        for (Order order : getAllOrders()) {
            if (order.getOrderId() > maxId) {
                maxId = order.getOrderId();
            }
        }

        return maxId;
    }

    public List<String> getOrderStatuses(Integer orderId) {
        Order order = getOrderById(orderId);

        switch (order.getOrderStatus()) {
            case OrderStatus.PENDING:
                return Arrays.asList(
                        OrderStatus.SHIPPED,
                        OrderStatus.CANCELED
                );
            case OrderStatus.SHIPPED:
                return Arrays.asList(
                        OrderStatus.DELIVERED,
                        OrderStatus.CANCELED
                );
            case OrderStatus.DELIVERED,
                 OrderStatus.CANCELED:
                return Arrays.asList(
                        OrderStatus.REFUNDED
                );
        }

        return null;
    }

    public Order getOrderById(Integer orderId) {
        for (Order order : getAllOrders()) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }

        return null;
    }

    public List<Order> getOrderByCustomer(Integer customerId) {
        List<Order> orders = new ArrayList<>();

        for (Order order : getAllOrders()) {
            if (order.getCustomerId() == customerId) {
                orders.add(order);
            }
        }

        return orders;
    }

    private void saveOrderToFile(List<Order> orders, boolean append) {
        FileManager fileManager = new FileManager();
        ProductManagement productManagement = new ProductManagement();
        BufferedWriter fileWriter = fileManager.getFileWriter(fileName, append);

        try {
            for (Order order : orders) {
                fileWriter.write(order.toString() + "," + productManagement.toRowString(order.getProducts()));
                fileWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printOrderDetails(Order order) {
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Order status: " + order.getOrderStatus());
        System.out.println("Order total: " + order.getAmount());
        System.out.println("Order products: ");
        for (Product product : order.getProducts()) {
            System.out.println(product.getName() + " (" + product.getQty() + "): " + product.getPrice());
        }
    }
}
