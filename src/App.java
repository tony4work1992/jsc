import java.util.Scanner;
import order_service.order_mgt.OrderSevice;
import product_service.product_mgt.ProductSevice;

public class App {
    public static void main(String[] args){
        try {
            Scanner scanner = new Scanner(System.in);
            int choice;
            ProductSevice productSevice = new ProductSevice();
            OrderSevice orderSevice = new OrderSevice();
            do {
                System.out.println("\n--- E-Commerce Application ---");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Place Order");
                System.out.println("4. View Order");
                System.out.println("5. Update Order Status");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        productSevice.addProduct();
                        break;
                    case 2:
                        productSevice.viewProducts();
                        break;
                    case 3:
                        orderSevice.placeOrder(scanner);
                        break;
                    case 4:
                        orderSevice.viewOrder(scanner);
                        break;
                    case 5:
                        orderSevice.updateOrderStatus(scanner);
                        break;
                    case 6:
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 6);

            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
