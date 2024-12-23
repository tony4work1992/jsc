package product_service.product_mgt.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import product_service.product_mgt.model.Product;

public class ProductRepository {
    private static final String PRODUCTS_FILE = System.getProperty("user.dir") + "/src/source/products.txt";
    private Map<Integer, Product> products = new HashMap<>();
    private boolean isLoad = false;

    public void loadProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String description = parts[2];
                double price = Double.parseDouble(parts[3]);
                int stock = Integer.parseInt(parts[4]);
                products.put(id, new Product(id, name, description, price, stock));
            }
            isLoad = true;
            System.out.println("load product");
        } catch (IOException e) {
            System.out.println("Error loading products: " + e.getMessage());
        }
    }

    public void addProduct(Product product) {
        File file = new File(PRODUCTS_FILE);
        try (
            FileWriter fw = new FileWriter(file, true); // 'true' enables append mode
            BufferedWriter writer = new BufferedWriter(fw)
        ){
            if (file.exists() && file.length() > 0) {
                writer.write("\n");
            }
            writer.write(product.getId() + "," + product.getName() + "," + product.getDescription() + "," + product.getPrice() + "," + product.getStockQty());
        } catch (IOException e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
        isLoad = false;
        System.out.println("Product added successfully!");
    }

    public Map<Integer, Product> getListProduct() {
        if(!isLoad) {
            loadProducts();
        }
        return products;
    }

    public Product getProductById(int productId) {
        if(!isLoad) {
            loadProducts();
        }
        if(!products.containsKey(productId)) {
            throw new NoSuchElementException("Product with ID " + productId + " does not exist.");
        }

        return products.get(productId);
    }

    public int getLastRealProductId()
    {
        if(!isLoad) {
            loadProducts();
        }
        return products.isEmpty() ? 1 : products.get(products.size()).getId();
    }

    public void updateProduct(int productId, Product product) {
        File file = new File(PRODUCTS_FILE);

        // Read all lines from the file
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                if(id == productId) {
                    line = product.getId() + "," + product.getName() + "," + product.getDescription() + "," + product.getPrice() + "," + product.getStockQty();
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading lines products: " + e.getMessage());
        }

        // Write the updated lines back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Error updated lines products: " + e.getMessage());
        }
        isLoad = false;
        System.out.println("Product updated successfully!");
    }
}
