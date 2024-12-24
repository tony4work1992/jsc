package smartosc.jsc.applications.ecom.modules.core;

import smartosc.jsc.applications.ecom.modules.customers.model.Customer;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu extends AbstractAction {
    public static final Scanner scanner = new Scanner(System.in);

    public void execute() throws IOException {
        System.out.println("Welcome to Ecommerce Application");
        // Enter you name
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        // Enter your email
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        // Enter your address
        System.out.println("Enter your address: ");
        String address = scanner.nextLine();
        Customer customer = new Customer();
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setName(name);
        while (this.keepRunning) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1", "2", "3", "4":
                    ActionFactory.getAction(choice).execute();
                    break;
                case "5":
                    this.keepRunning = false;
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Ecommerce Application ===");
        System.out.println("1. Manage your information");
        System.out.println("2. Manage Product");
        System.out.println("3. Manage Order");
        System.out.println("4. Manage Promotion");
        System.out.println("5. Exit");
    }
}
