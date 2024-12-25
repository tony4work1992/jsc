package smartosc.jsc.applications.ecom.modules.products.cli;

import smartosc.jsc.applications.ecom.modules.core.AbstractAction;
import smartosc.jsc.applications.ecom.modules.products.model.Product;
import smartosc.jsc.applications.ecom.modules.products.repository.ProductFileRepository;

import java.io.IOException;
import java.util.List;

import static smartosc.jsc.applications.ecom.modules.core.MainMenu.scanner;

public class ProductAction extends AbstractAction {

    private ProductFileRepository productFileRepository = new ProductFileRepository();
    public void execute() throws IOException {

        while (this.keepRunning) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    editProduct();
                    break;
                case "3":
                    deleteProduct();
                    break;
                case "4":
                    getProductByName();
                    break;
                case "5":
                    this.viewProducts();
                    break;
                case "6":
                    this.keepRunning = false;
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void getProductByName() {
        System.out.println("\n=== Search Product By Name ===");
        String name = promptInput("Enter product name: ");
        try {
            List<Product> products = this.productFileRepository.getByName(name);
            if (products.isEmpty()) {
                System.out.println("No products found.");
                return;
            }

            String format = "%-10s | %-15s | %-20s | %-10s | %-10s%n";
            System.out.printf(format, Product.PRODUCT_ID, Product.NAME, Product.DESCRIPTION, Product.PRICE, Product.QUANTITY);
            System.out.println("----------------------------------------------------------------------------");

            for (Product product : products) {
                System.out.printf(format, product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void printMenu() {
        super.printMenu();
        System.out.println("=== Product Management ===");
        System.out.println("1. Add Product");
        System.out.println("2. Edit Product");
        System.out.println("3. Delete Product");
        System.out.println("4. Search Product By Name (contains to lower case)");
        System.out.println("5. View Products");
        System.out.println("6. Exit");
    }

    private void addProduct() throws IOException {
        System.out.println("\n=== Add Product ===");
        Product product = new Product();
        product.setName(promptInput("Enter product name: "));
        product.setDescription(promptInput("Enter product description: "));
        product.setPrice(promptInput("Enter product price: "));
        product.setQuantity(promptInput("Enter product quantity: "));
        this.productFileRepository.save(product);
        this.viewProducts();
        System.out.println("Product added successfully!");
    }

    private void editProduct() throws IOException {
        System.out.println("\n=== Edit Product ===");
        this.viewProducts();
        String index = promptIndex();
        Product product = this.productFileRepository.getById(index);
        if (product.getId() == null) {
            System.out.println("Invalid product index.");
            return;
        }

        product.setName(promptInput("Enter product name: ", product.getName()));
        product.setDescription(promptInput("Enter product description: ", product.getDescription()));
        product.setPrice(promptInput("Enter product price: ", product.getPrice()));
        product.setQuantity(promptInput("Enter product quantity: ", product.getQuantity()));
        this.productFileRepository.save(product);
        this.viewProducts();
        System.out.println("Product updated successfully!");
    }

    private void deleteProduct() throws IOException {
        System.out.println("\n=== Delete Product ===");
        this.viewProducts();
        String index = promptIndex();
        this.productFileRepository.deleteById(index);
        this.viewProducts();
    }

    private void viewProducts() throws IOException {
        System.out.println("\n=== Product List ===");
        List<Product> products = this.productFileRepository.getAll();
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        // Print table headers
        String format = "%-10s | %-15s | %-20s | %-10s | %-10s%n";
        System.out.printf(format, Product.PRODUCT_ID, Product.NAME, Product.DESCRIPTION, Product.PRICE, Product.QUANTITY);
        System.out.println("----------------------------------------------------------------------------");

        // Loop through the products and print rows
        for (Product product : products) {
            System.out.printf(format, product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
        }
    }

    private static String promptInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String promptInput(String prompt, String defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? defaultValue : input;
    }

    private static String promptIndex() {
        System.out.print("Enter product index: ");
        return scanner.nextLine();
    }
}
