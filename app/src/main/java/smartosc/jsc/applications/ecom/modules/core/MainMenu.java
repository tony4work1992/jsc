package smartosc.jsc.applications.ecom.modules.core;

import smartosc.jsc.applications.ecom.modules.customers.cli.CustomerAction;
import smartosc.jsc.applications.ecom.modules.customers.model.Session;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu extends AbstractAction {
    public static final Scanner scanner = new Scanner(System.in);

    public void execute() throws IOException {
        System.out.println("Welcome to Ecommerce Application");

        CustomerAction customerAction = new CustomerAction();
        customerAction.execute();

        while (this.keepRunning) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1", "2", "3":
                    ActionFactory.getAction(choice).execute();
                    break;
                case "4":
                    this.keepRunning = false;
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    protected void printMenu() {
        super.printMenu();
        System.out.println("=== Ecommerce Application ===");
        System.out.println(("What do you want to do?"));
        System.out.println("1. Manage Product");
        System.out.println("2. Manage Order");
        System.out.println("3. Manage Promotion");
        System.out.println("4. Exit");
    }
}
