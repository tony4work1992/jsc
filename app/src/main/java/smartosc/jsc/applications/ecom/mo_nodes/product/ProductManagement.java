package smartosc.jsc.applications.ecom.mo_nodes.product;

import smartosc.jsc.applications.ecom.ba_nodes.FileManager;
import smartosc.jsc.applications.ecom.mo_nodes.product.model.Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProductManagement {
    private List<Product> products = new ArrayList<>();
    private final String fileName = "products.txt";

    public ProductManagement() {
        loadProducts();
    }

    public void addProduct(Product product) {
        products.add(product);
        saveProductsToFile(products);
    }

    public void updateProduct(int id, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.set(i, updatedProduct);
                saveProductsToFile(products);

                return;
            }
        }

        System.out.println("Product not found!");
    }

    public void deleteProduct(int id) {
        products.removeIf(product -> product.getId().equals(id));
        saveProductsToFile(products);
    }

    public List<Product> getProductByIds(String productIds) {
        List<Product> productList = new ArrayList<>();
        List<String> ids = Arrays.asList(productIds.split(","));

        products.stream().filter(product -> ids.contains(product.getId())).forEach(product -> {
            productList.add(product);
        });

        return productList;
    }

    public void loadProducts() {
        String row;
        FileManager fileManager = new FileManager();
        BufferedReader fileReader = fileManager.getFileReader(fileName);

        try {
            while ((row = fileReader.readLine()) != null) {
                String[] parts = row.split(",");

                if (parts.length == 5) {
                    Product product = new Product(
                            Integer.parseInt(parts[0].trim()),
                            parts[1].trim(),
                            parts[2].trim(),
                            Double.parseDouble(parts[3].trim()),
                            Integer.parseInt(parts[4].trim())
                    );

                    products.add(product);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listProducts() {
        products.forEach(System.out::println);
        System.out.println();
    }

    public void searchProduct(String searchKey, String value) {
        switch (searchKey) {
            case "id":
                products.stream()
                        .filter(product -> product.getId().equals(Integer.parseInt(value)))
                        .forEach(System.out::println);
                break;
            case "name":
                products.stream()
                        .filter(product -> product.getName().equals(value))
                        .forEach(System.out::println);
                break;
            case "description":
                products.stream()
                        .filter(product -> product.getDescription().equals(value))
                        .forEach(System.out::println);
                break;
            case "price":
                products.stream()
                        .filter(product -> product.getPrice() == Double.parseDouble(value))
                        .forEach(System.out::println);
                break;
            case "stock":
                products.stream()
                        .filter(product -> product.getQty() == Integer.parseInt(value))
                        .forEach(System.out::println);
                break;
            default:
                System.out.println("Invalid search key!");
        }
    }

    private void saveProductsToFile(List<Product> products) {
        FileManager fileManager = new FileManager();
        BufferedWriter fileWriter = fileManager.getFileWriter(fileName, false);

        try {
            for (Product product : products) {
                fileWriter.write(product.toString());
                fileWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String toRowString(List<Product> products) {
        StringBuilder rowString = new StringBuilder();

        for (Product product : products) {
            rowString.append(product.getId()).append(",");
        }

        return rowString.toString();
    }

    public Product buildProductMap() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        int Id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Stock Quantity: ");
        int stock = scanner.nextInt();

        return new Product(Id, name, description, price, stock);
    }
}
