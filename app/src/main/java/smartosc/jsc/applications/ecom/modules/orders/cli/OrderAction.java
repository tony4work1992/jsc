package smartosc.jsc.applications.ecom.modules.orders.cli;

import smartosc.jsc.applications.ecom.modules.core.AbstractAction;
import smartosc.jsc.applications.ecom.modules.customers.model.Session;
import smartosc.jsc.applications.ecom.modules.orders.constant.OrderStatus;
import smartosc.jsc.applications.ecom.modules.orders.model.Order;
import smartosc.jsc.applications.ecom.modules.orders.model.OrderItem;
import smartosc.jsc.applications.ecom.modules.orders.repository.OrderFileRepository;
import smartosc.jsc.applications.ecom.modules.orders.repository.OrderItemFileRepository;
import smartosc.jsc.applications.ecom.modules.products.model.Product;
import smartosc.jsc.applications.ecom.modules.products.repository.ProductFileRepository;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static smartosc.jsc.applications.ecom.modules.core.MainMenu.scanner;

public class OrderAction extends AbstractAction {

    private Order order = new Order(Session.getCustomerSession().get("email"), OrderStatus.PENDING.getStatus(), "0");
    // empty list
    private List<OrderItem> orderItems = new LinkedList<>();

    @Override
    public void execute() throws IOException {
        while (keepRunning) {
            this.printMenu();
            String action = scanner.nextLine();
            switch (action) {
                case "1":
                    this.addProductToCart();
                    break;
                case "2":
                    this.placeOrder();
                    break;
                case "3":
                    this.viewCart();
                    break;
                case "4":
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid action");
                    break;
            }
        }
    }

    private void viewCart() {
        if (this.orderItems.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        String format = "%-10s | %-15s | %-10s | %-10s | %-10s%n";
        System.out.printf(format, "Product ID", "Product Name", "Quantity", "Price", "Total");
        System.out.println("-------------------------------------------------------------");
        this.orderItems.forEach(
                orderItem -> {
                    System.out.printf(format, orderItem.getProductId(), orderItem.getName(), orderItem.getQuantity(), orderItem.getPrice(), orderItem.getQuantity() * orderItem.getPrice());
                }
        );
        System.out.println("-------------------------------------------------------------");
        System.out.println("Total price: " + this.order.getTotalPrice());
    }

    private void placeOrder() throws IOException {
        if (this.orderItems.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        new OrderFileRepository().save(this.order);
        String orderId = this.order.getId();
        this.orderItems.forEach(
                orderItem -> {
                    orderItem.setOrderId(orderId);
                    try {
                        new OrderItemFileRepository().save(orderItem);
                        Product product = new ProductFileRepository().getById(orderItem.getProductId());
                        product.setQuantity(String.valueOf(Integer.parseInt(product.getQuantity()) - orderItem.getQuantity()));
                        new ProductFileRepository().save(product);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        System.out.println("Order placed successfully");
    }

    private void addProductToCart() throws IOException {
        System.out.println(("Please enter product id: "));
        String productId = scanner.nextLine();
        System.out.println(("Please enter quantity: "));
        String quantity = scanner.nextLine();
        Product product = new ProductFileRepository().getById(productId);
        if (product.getId() == null) {
            System.out.println("Product not found");
            return;
        }

        if (Integer.parseInt(product.getQuantity()) < Integer.parseInt(quantity)) {
            System.out.println("Not enough product in stock");
            return;
        }
        this.order.setTotalPrice(String.valueOf(
                Double.parseDouble(this.order.getTotalPrice())
                        + (Double.parseDouble(product.getPrice()) * Integer.parseInt(quantity)))
        );
        OrderItem orderItem = new OrderItem(
                product.getId(),
                product.getName(),
                Integer.parseInt(quantity),
                Double.parseDouble(product.getPrice())
        );
        this.orderItems.add(orderItem);
        System.out.println("Product added to cart");
    }

    @Override
    protected void printMenu() {
        super.printMenu();
        System.out.println("=== Order Management ===");
        System.out.println("1. Add a product to cart");
        System.out.println("2. Place order");
        System.out.println("3. View cart");
        System.out.println("4. Back to main menu");
    }
}
