package smartosc.jec.ecommerce.main;

import smartosc.jec.ecommerce.product.model.Product;
import smartosc.jec.ecommerce.product.service.IProductService;
import smartosc.jec.ecommerce.product.service.ProductServiceImpl;
import smartosc.jec.ecommerce.order.model.Order;
import smartosc.jec.ecommerce.order.service.IOrderService;
import smartosc.jec.ecommerce.order.service.OrderServiceImpl;
import smartosc.jec.ecommerce.promotion.model.Promotion;
import smartosc.jec.ecommerce.promotion.model.PromotionFactory;
import smartosc.jec.ecommerce.promotion.service.IPromotionService;
import smartosc.jec.ecommerce.promotion.service.PromotionServiceImpl;
import java.util.*;

public class EcommerceApp {
    private static IProductService productService = new ProductServiceImpl();
    private static IOrderService orderService = new OrderServiceImpl();
    private static IPromotionService promotionService = new PromotionServiceImpl();
    private static PromotionFactory promotionFactory = new PromotionFactory();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nE-commerce Application");
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Orders");
            System.out.println("3. Manage Promotions");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    manageOrders();
                    break;
                case 3:
                    managePromotions();
                    break;
                case 4:
                    System.out.println("Exiting application...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageProducts() {
        while (true) {
            System.out.println("\nManage Products");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View All Products");
            System.out.println("5. Search Product by ID");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    viewAllProducts();
                    break;
                case 5:
                    searchProductById();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Product Stock Quantity: ");
        int stockQuantity = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        Product product = new Product(id, name, description, price, stockQuantity);
        productService.addProduct(product);
        System.out.println("Product added successfully!");
    }

    private static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        String id = scanner.nextLine();
        Product product = productService.findProductById(id);
        if (product != null) {
            System.out.print("Enter new Product Name (leave empty to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) product.setName(name);

            System.out.print("Enter new Product Description (leave empty to keep current): ");
            String description = scanner.nextLine();
            if (!description.isEmpty()) product.setDescription(description);

            System.out.print("Enter new Product Price (leave empty to keep current): ");
            String priceStr = scanner.nextLine();
            if (!priceStr.isEmpty()) product.setPrice(Double.parseDouble(priceStr));

            System.out.print("Enter new Product Stock Quantity (leave empty to keep current): ");
            String stockStr = scanner.nextLine();
            if (!stockStr.isEmpty()) product.setStockQuantity(Integer.parseInt(stockStr));

            productService.updateProduct(product);
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found!");
        }
    }

    private static void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        String id = scanner.nextLine();
        Product product = productService.findProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found!");
        }
    }

    private static void viewAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private static void searchProductById() {
        System.out.print("Enter Product ID to search: ");
        String id = scanner.nextLine();
        Product product = productService.findProductById(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found!");
        }
    }

    private static void manageOrders() {
        while (true) {
            System.out.println("\nManage Orders");
            System.out.println("1. Add Order");
            System.out.println("2. Update Order");
            System.out.println("3. Delete Order");
            System.out.println("4. View All Orders");
            System.out.println("5. Search Order by ID");
            System.out.println("6. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    updateOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 4:
                    viewAllOrders();
                    break;
                case 5:
                    searchOrderById();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addOrder() {
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter comma-separated Product IDs (e.g., P1,P2,P3): ");
        String productsStr = scanner.nextLine();
        List<String> products = Arrays.asList(productsStr.split(","));
        System.out.print("Enter Total Price: ");
        double totalPrice = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline character
        System.out.print("Enter Order Status: ");
        String status = scanner.nextLine();

        Order order = new Order(orderId, products, totalPrice, status);
        orderService.addOrder(order);
        System.out.println("Order added successfully!");
    }

    private static void updateOrder() {
        System.out.print("Enter Order ID to update: ");
        String orderId = scanner.nextLine();
        Order order = orderService.findOrderById(orderId);
        if (order != null) {
            System.out.print("Enter new Products (comma-separated, leave empty to keep current): ");
            String productsStr = scanner.nextLine();
            if (!productsStr.isEmpty()) {
                order.setProducts(Arrays.asList(productsStr.split(",")));
            }

            System.out.print("Enter new Total Price (leave empty to keep current): ");
            String priceStr = scanner.nextLine();
            if (!priceStr.isEmpty()) {
                order.setTotalPrice(Double.parseDouble(priceStr));
            }

            System.out.print("Enter new Status (leave empty to keep current): ");
            String status = scanner.nextLine();
            if (!status.isEmpty()) {
                order.setStatus(status);
            }

            orderService.updateOrder(order);
            System.out.println("Order updated successfully!");
        } else {
            System.out.println("Order not found!");
        }
    }

    private static void deleteOrder() {
        System.out.print("Enter Order ID to delete: ");
        String orderId = scanner.nextLine();
        Order order = orderService.findOrderById(orderId);
        if (order != null) {
            orderService.deleteOrder(orderId);
            System.out.println("Order deleted successfully!");
        } else {
            System.out.println("Order not found!");
        }
    }

    private static void viewAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    private static void searchOrderById() {
        System.out.print("Enter Order ID to search: ");
        String orderId = scanner.nextLine();
        Order order = orderService.findOrderById(orderId);
        if (order != null) {
            System.out.println(order);
        } else {
            System.out.println("Order not found!");
        }
    }

    private static void managePromotions() {
        while (true) {
            System.out.println("\nManage Promotions");
            System.out.println("1. Add Promotion");
            System.out.println("2. View Promotion by Code");
            System.out.println("3. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    addPromotion();
                    break;
                case 2:
                    viewPromotionByCode();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addPromotion() {
        System.out.print("Enter Promo Code: ");
        String promoCode = scanner.nextLine();
        System.out.print("Enter Promotion Type (percentage/flat): ");
        String type = scanner.nextLine();
        System.out.print("Enter Promotion Value: ");
        double value = scanner.nextDouble();
        scanner.nextLine();  // Consume the newline character
        Promotion promotion = promotionFactory.createPromotion(promoCode, type, value);
        promotionService.addPromotion(promotion);
        System.out.println("Promotion added successfully!");
    }

    private static void viewPromotionByCode() {
        System.out.print("Enter Promo Code to search: ");
        String promoCode = scanner.nextLine();
        Promotion promotion = promotionService.getPromotionByCode(promoCode);
        if (promotion != null) {
            System.out.println(promotion);
        } else {
            System.out.println("Promotion not found!");
        }
    }
}