import commerce_management.customer.model.Customer;
import commerce_management.customer.services.CustomerService;
import commerce_management.order.services.OrderService;
import commerce_management.product.model.Product;
import commerce_management.product.services.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService(productService, customerService);
       
        while (true) {
            System.out.println("E-Commerce Application");
            System.out.println("1. Add Product");
            System.out.println("2. List Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Add Customer");
            System.out.println("6. List Customers");
            System.out.println("7. Update Customer");
            System.out.println("8. Delete Customer");
            System.out.println("9. Place Order");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter product details (ID, Name, Description, Price, Stock): ");
                        String[] details = scanner.nextLine().split(",");
                        Product product = new Product(Integer.parseInt(details[0]));
                        product.setName(details[1]).setDescription(details[2]).setPrice(Double.parseDouble(details[3]))
                                .setStockQuantity(Integer.parseInt(details[4]));
                        productService.addProduct(product);
                        System.out.println("Product added successfully!");
                    }
                    case 2 -> productService.listProducts();
                    case 3 -> {
                        System.out.print("Enter product ID to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter updated details (Name, Description, Price, Stock): ");
                        String[] details = scanner.nextLine().split(",");
                        productService.updateProduct(id, details[0], details[1], Double.parseDouble(details[2]),
                                Integer.parseInt(details[3]));
                    }
                    case 4 -> {
                        System.out.print("Enter product ID to delete: ");
                        int id = scanner.nextInt();
                        productService.deleteProduct(id);
                    }
                    case 5 -> {
                        System.out.print("Enter customer details (ID, Name, Email): ");
                        String[] details = scanner.nextLine().split(",");
                        Customer customer = new Customer(Integer.parseInt(details[0]), details[1], details[2]);
                        customerService.addCustomer(customer);
                        System.out.println("Customer added successfully!");
                    }
                    case 6 -> customerService.listCustomers();
                    case 7 -> {
                        System.out.print("Enter customer ID to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter updated details (Name, Email): ");
                        String[] details = scanner.nextLine().split(",");
                        customerService.updateCustomer(id, details[0], details[1]);
                    }
                    case 8 -> {
                        System.out.print("Enter customer ID to delete: ");
                        int id = scanner.nextInt();
                        customerService.deleteCustomer(id);
                    }
                    case 9 -> {
                        System.out.print("Enter Customer ID: ");
                        int customerId = scanner.nextInt();

                        System.out.print("Enter Promotion ID: ");
                        int promotionId = scanner.nextInt();

                        System.out.print("Enter Product IDs (comma-separated): ");
                        scanner.nextLine(); // Consume newline
                        String[] productIdStrings = scanner.nextLine().split(",");
                        List<Integer> productIds = new ArrayList<>();
                        for (String id : productIdStrings) {
                            productIds.add(Integer.parseInt(id.trim()));
                        }

                        orderService.createOrder(customerId, productIds, promotionId);
                    }
                    case 11 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
    }

}
