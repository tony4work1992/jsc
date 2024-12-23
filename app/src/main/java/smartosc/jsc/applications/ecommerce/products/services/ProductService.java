package smartosc.jsc.applications.ecommerce.products.services;

import smartosc.jsc.applications.ecommerce.products.models.Product;
import smartosc.jsc.applications.ecommerce.utils.FileUtils;

import java.util.*;

public class ProductService implements IProductService{
    private final Map<Integer, Product> products = new HashMap<>();
    private final Scanner scanner;
    private String filePath = "/products.csv";
    private final String folderPath;

    public ProductService(Scanner scanner, String folderPath) {
        this.scanner = scanner;
        this.folderPath = folderPath;
        this.filePath = folderPath + filePath;
        loadData();
    }

    public void manageProducts() {
        while (true) {
            System.out.println("\nProduct Management");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. List Products");
            System.out.println("5. Go Back");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createProduct();
                case 2 -> updateProduct();
                case 3 -> deleteProductById();
                case 4 -> getAllProducts();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }


    @Override
    public void createProduct() {
        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Product SKU: ");
        String sku = scanner.nextLine();

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Stock Quantity: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        products.put(id, new Product(id, sku, name, price, stock));
        saveData();
        System.out.println("Product added successfully!");
    }

    @Override
    public void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product product = products.get(id);
        if (product != null) {
            System.out.print("Enter new SKU (current: " + product.getSku() + "): ");
            product.setSku(scanner.nextLine());

            System.out.print("Enter new Name (current: " + product.getName() + "): ");
            product.setName(scanner.nextLine());

            System.out.print("Enter new Price (current: " + product.getPrice() + "): ");
            product.setPrice(scanner.nextDouble());

            System.out.print("Enter new Stock (current: " + product.getStock() + "): ");
            product.setStock(scanner.nextInt());
            scanner.nextLine();
            saveData();
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public Product getProductById(int id) {
        return products.get(id);
    }

    @Override
    public void getAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("\nProduct List:");
            for (Product product : products.values()) {
                System.out.println(product);
            }
        }
    }

    @Override
    public void deleteProductById() {
        System.out.print("Enter Product ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (products.remove(id) != null) {
            saveData();
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void loadData() {
        ArrayList<String> data = FileUtils.loadDataFromFile(filePath);
        for(String item: data){
            String[] parts = item.split(",");
            int id = Integer.parseInt(parts[0]);
            String sku = parts[1];
            String name = parts[2];
            double price = Double.parseDouble(parts[3]);
            int stock = Integer.parseInt(parts[4]);
            products.put(id, new Product(id, sku, name, price, stock));
        }
    }

    private void saveData() {
        ArrayList<String> data = new ArrayList<>();
        for (Product product : products.values()) {
            data.add(product.toCsv());
        }
        FileUtils.saveDataToFile(folderPath, filePath, data);
    }
}
