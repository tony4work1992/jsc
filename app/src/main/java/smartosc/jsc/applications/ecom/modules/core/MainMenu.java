package smartosc.jsc.applications.ecom.modules.core;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu extends AbstractAction {
    private static final Scanner scanner = new Scanner(System.in);

    public void execute() throws IOException {
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

    private static void printMenu() {
        System.out.println("\n=== Ecommerce Application ===");
        System.out.println("1. Manage Product");
        System.out.println("2. Manage Order");
        System.out.println("3. Manage Promotion");
        System.out.println("4. Exit");
    }
}
