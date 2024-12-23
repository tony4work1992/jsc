package commerce_management.product.services;

import commerce_management.product.model.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductService {
    private static final String FILE_NAME = "src/data/products.txt";
    private List<Product> products;

    public ProductService() throws IOException {
        File file = new File(FILE_NAME);
        try {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs(); // Ensure the directory exists
            }
            if (!file.exists()) {
                file.createNewFile(); // Create the file if it does not exist
            }
        } catch (IOException e) {
            throw new IOException ("Error initializing products file: " + e.getMessage());
        }
        products = new ArrayList<>();
        loadProducts();
    }

    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) throws Exception {
        if (isProductIdExists(product.getProductId())) {
            throw new Exception ("Product with ID " + product.getProductId() + " already exists.");
        }
        products.add(product);
        saveProducts();
    }

    public void listProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private void saveProducts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product product : products) {
                writer.write(product.getProductId() + "," + product.getName() + "," + product.getDescription() + ","
                        + product.getPrice() + "," + product.getStockQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
    }

    private boolean isProductIdExists(int id) {
        for (Product product : products) {
            if (product.getProductId() == id) {
                return true;
            }
        }
        return false;
    }

    public void updateProduct(int id, String name, String description, double price, int stockQuantity) {
        for (Product product : products) {
            if (product.getProductId() == id) {
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
                product.setStockQuantity(stockQuantity);
                saveProducts();
                System.out.println("Product updated successfully.");
                return;
            }
        }
        System.out.println("Error: Product with ID " + id + " not found.");
    }

    public void deleteProduct(int id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId() == id) {
                iterator.remove();
                saveProducts();
                System.out.println("Product deleted successfully.");
                return;
            }
        }
        System.out.println("Error: Product with ID " + id + " not found.");
    }


    private void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String description = parts[2];
                double price = Double.parseDouble(parts[3]);
                int stockQuantity = Integer.parseInt(parts[4]);
                Product product = new Product(id);
                product.setName(name).setDescription(description).setPrice(price).setStockQuantity(stockQuantity);
                products.add(product);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Product file not found. Starting with an empty product list.");
        } catch (IOException e) {
            System.out.println("Error loading products: " + e.getMessage());
        }
    }
}
