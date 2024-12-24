package smartosc.jsc.applications.ecom;

import smartosc.jsc.applications.ecom.mo_nodes.cart.CartManagement;
import smartosc.jsc.applications.ecom.mo_nodes.cart.model.Cart;
import smartosc.jsc.applications.ecom.mo_nodes.customer.CustomerManagement;
import smartosc.jsc.applications.ecom.mo_nodes.customer.model.Customer;
import smartosc.jsc.applications.ecom.mo_nodes.order.OrderManagement;
import smartosc.jsc.applications.ecom.mo_nodes.order.model.Order;
import smartosc.jsc.applications.ecom.mo_nodes.product.ProductManagement;
import smartosc.jsc.applications.ecom.mo_nodes.product.model.Product;
import smartosc.jsc.applications.ecom.mo_nodes.promotion.PromotionManagement;
import smartosc.jsc.applications.ecom.mo_nodes.promotion.model.Promotion;

import java.util.List;
import java.util.Scanner;

public class EComApp {
    Customer customer = null;
    Cart cart = null;
    Scanner scanner = new Scanner(System.in);

    public void runApp() {
        boolean running = true;
        CustomerManagement customerManagement = new CustomerManagement();

        while (running) {
            System.out.println("=== E-commerce Application ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            switch (scanner.nextInt()) {
                case 1:
                    System.out.print("Enter your ID: ");
                    int customerId = scanner.nextInt();
                    this.customer = customerManagement.login(customerId);

                    running = process();
                    break;
                case 2:
                    System.out.print("Enter your ID: ");
                    int newCustomerId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter your Name: ");
                    String newCustomerName = scanner.nextLine();
                    System.out.print("Enter your Email: ");
                    String newCustomerEmail = scanner.nextLine();

                    this.customer = customerManagement.register(newCustomerId, newCustomerName, newCustomerEmail);
                    running = process();
                    break;
                case 3:
                    System.out.println("Exiting the application...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public boolean process() {
        boolean loggedSession = true;

        while (loggedSession) {
            System.out.println("=== E-commerce Application ===");
            System.out.println("1. Product Management");
            System.out.println("2. Cart Management");
            System.out.println("3. Order Management");
            System.out.println("4. Exit");

            switch (scanner.nextInt()) {
                case 1: productManagementMenu(); break;
                case 2: cartManagementMenu(); break;
                case 3: orderManagementMenu(); break;
                case 4: loggedSession = false; break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }

        return loggedSession;
    }

    public void productManagementMenu() {
        boolean managingProducts = true;
        ProductManagement productManagement = new ProductManagement();

        while (managingProducts) {
            System.out.println("=== Product Management ===");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Remove Product");
            System.out.println("4. List All Products");
            System.out.println("5. Search Product");
            System.out.println("6. Back");

            int productChoice = scanner.nextInt();
            switch (productChoice) {
                case 1:
                    System.out.print("=== Add New Product ===\n");
                    Product newProduct = productManagement.buildProductMap();

                    productManagement.addProduct(newProduct);
                    break;
                case 2:
                    System.out.print("=== Update Product ===\n");

                    Product updateProduct = productManagement.buildProductMap();
                    productManagement.updateProduct(updateProduct.getId(), updateProduct);
                    break;
                case 3:
                    System.out.print("=== Remove Product ===\n");
                    System.out.print("Enter Product ID: ");
                    int productId = scanner.nextInt();

                    productManagement.deleteProduct(productId);
                    break;
                case 4:
                    System.out.println("=== All Product ===");
                    productManagement.listProducts();
                    break;
                case 5:
                    System.out.println("=== Search Product ===\n");
                    System.out.print("Enter search key: ");
                    String searchKey = scanner.nextLine();
                    System.out.print("Enter value: ");
                    String value = scanner.nextLine();

                    System.out.println("Result:\n");
                    productManagement.searchProduct(searchKey, value);
                    break;
                case 6:
                    managingProducts = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void cartManagementMenu() {
        boolean managingCart = true;
        CartManagement cartManagement = new CartManagement();
        ProductManagement productManagement = new ProductManagement();
        PromotionManagement promotionManagement = new PromotionManagement();

        while (managingCart) {
            System.out.println("=== Cart Management ===");
            System.out.println("1. Add to Cart");
            System.out.println("2. Remove from Cart");
            System.out.println("3. Apply Discount");
            System.out.println("4. Cart Information");
            System.out.println("5. Exit");

            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("=== Add to Cart ===\n");
                    productManagement.listProducts();

                    System.out.println("Select Product IDs: ");
                    System.out.println("Note: Split by Comma \",\" ");

                    String newCartItemIds = scanner.nextLine();
                    List<Product> addItems = productManagement.getProductByIds(newCartItemIds);

                    if(this.cart == null) {
                        this.cart = new Cart(this.customer.getId());
                    }

                    this.cart = cartManagement.addProductToCart(this.cart, addItems);
                    System.out.println("Add cart successful!");
                    break;
                case 2:
                    System.out.println("=== Remove product from Cart ===\n");
                    this.cart.getProducts().forEach(product -> {
                        System.out.println(product.toString());
                    });

                    System.out.println("\nEnter Product IDs: ");
                    System.out.println("Note: Split by Comma \",\" ");
                    String removeCartItemIds = scanner.nextLine();
                    List<Product> removeItems = productManagement.getProductByIds(removeCartItemIds);

                    cartManagement.removeProductFromCart(this.cart, removeItems);
                    System.out.println("Remove items successful!");
                    break;
                case 3:
                    System.out.println("=== Apply Discount ===\n");
                    System.out.println("Available discounts: \n");

                    List<Promotion> promotions = promotionManagement.getAvailablePromotions(this.customer);
                    for (Promotion promotion : promotions) {
                        System.out.println(promotion.toString());
                    }

                    System.out.println("\nEnter Product IDs: ");
                    System.out.println("Note: Split by Comma \",\" ");

                    String selectedDiscountIds = scanner.nextLine();
                    List<Promotion> selectedDiscounts = promotionManagement.getByIds(selectedDiscountIds, this.customer);
                    cartManagement.applyDiscount(this.cart, selectedDiscounts);

                    System.out.println("Apply discount successful!");
                    break;
                case 4:
                    System.out.println("=== Cart Information ===\n");
                    System.out.println("- Customer ID: " + this.cart.getCustomerId());
                    System.out.println("- Total: " + this.cart.getTotal());
                    System.out.println("- Qty: " + this.cart.getQty());
                    System.out.println("- Products: \n");

                    this.cart.getProducts().forEach(product -> {
                        System.out.println(product.toString());
                    });

                    System.out.println();
                    break;
                case 5:
                    managingCart = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void orderManagementMenu() {
        boolean managingOrders = true;
        OrderManagement orderManagement = new OrderManagement();

        while (managingOrders) {
            System.out.println("=== Order Management ===");
            System.out.println("1. Place Order");
            System.out.println("2. Change Order Status");
            System.out.println("3. View My Orders");
            System.out.println("4. Exit");

            int orderChoice = scanner.nextInt();
            switch (orderChoice) {
                case 1:
                    if (this.cart == null) {
                        System.out.println("Cart is empty. Please add items to cart first.");
                        break;
                    }

                    System.out.println("=== Place Order ===\n");
                    Order newOrder = orderManagement.placeOrder(this.cart, this.customer);

                    if (newOrder == null) {
                        System.out.println("Failed to place order. Please try again.");
                        break;
                    }

                    System.out.println("Order placed successfully!");
                    orderManagement.printOrderDetails(newOrder);
                    break;
                case 2:
                    System.out.println("=== Change Order Status ===\n");
                    orderManagement.getOrderByCustomer(this.customer.getId()).forEach(order -> {
                        System.out.println(order.toString());
                    });

                    System.out.print("Select Order ID: ");
                    int changedOrderId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Select Status: ");
                    List<String> changedOrderStatuses = orderManagement.getOrderStatuses(changedOrderId);
                    changedOrderStatuses.forEach(System.out::println);
                    String changedOrderStatus = scanner.nextLine();

                    orderManagement.updateOrderStatus(changedOrderId, changedOrderStatus);
                    System.out.println("Order status updated to: " + changedOrderStatus);
                    break;
                case 3:
                    System.out.println("=== View My Orders ===\n");
                    orderManagement.getOrderByCustomer(this.customer.getId()).forEach(order -> {
                        System.out.println(order.toString());
                    });
                    break;
                case 4:
                    managingOrders = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
