package commerce_management.order.services;

import commerce_management.customer.model.Customer;
import commerce_management.customer.services.CustomerService;
import commerce_management.order.model.Order;
import commerce_management.product.model.Product;
import commerce_management.product.services.ProductService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static final String FILE_NAME = "src/data/orders.txt";
    private List<Order> orders;
    private ProductService productService;
    private CustomerService customerService;

    public OrderService(ProductService productService, CustomerService customerService) throws IOException {
        this.productService = productService;
        this.customerService = customerService;
        File file = new File(FILE_NAME);
        try {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs(); // Ensure the directory exists
            }
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it does not exist
            }
        } catch (IOException e) {
            throw new IOException("Error initializing order file: " + e.getMessage());
        }
        orders = new ArrayList<>();
    }

    public void createOrder(int customerId, List<Integer> productIds, int promotionId) {
        List<Product> products = new ArrayList<>();
        double totalPrice = 0.0;

        for (int productId : productIds) {
            Product product = productService.getProductById(productId);
            if (product == null) {
                System.out.println("Error: Product with ID " + productId + " not found.");
                return;
            }
            products.add(product);
            totalPrice += product.getPrice();
        }

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Error: Customer with ID " + customerId + " not found.");
            return;
        }

        Order order = new Order(customerId, products, totalPrice, promotionId);

        try {
            saveOrder(order);
            System.out.println("Order created successfully.");
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    public void saveOrder(Order order) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(order.toString());
        }
    }
}
