package smartosc.jsc.applications.jec.product_mgt.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import smartosc.jsc.applications.jec.file_handling.services.FileProcessingService;
import smartosc.jsc.applications.jec.product_mgt.models.Product;

public class ProductService {

    public static final String SOURCE = "app/src/main/java/smartosc/jsc/applications/jec/resources/products.csv";

    public List<Product> getProductsByCategory(Integer categoryId) {
        // Simulate fetching products by category
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        System.out.println("Fetching products for category ID: " + categoryId);

        FileProcessingService fileService = new FileProcessingService();

        List<Map<String, String>> productList = fileService.readCsvWithHeaders(SOURCE);

        return productList.stream()
                .filter(product -> categoryId == Integer.parseInt(product.get("categoryId")))
                .map(item -> {
                    return this.convert(item);
                })
                .collect(Collectors.toList());

        // Example: Fetch from database or any data source
    }

    public Product getProductDetails(Integer productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        System.out.println("Fetching details for product ID: " + productId);
        FileProcessingService fileService = new FileProcessingService();

        List<Map<String, String>> productList = fileService.readCsvWithHeaders(SOURCE);
        List<Product> products = productList.stream()
                .filter(product -> productId == Integer.parseInt(product.get("productId")))
                .map(item -> {
                    return this.convert(item);
                })
                .collect(Collectors.toList());

        if (products.isEmpty()) {
            return null;
        }

        return products.getFirst();

        // Example: Fetch from database or any data source
    }

    public Product convert(Map<String, String> data) {
        Integer productId = Integer.parseInt(data.get("productId"));
        Product product = new Product(productId);
        product.setProductId(productId);
        product.setCategoryId(Integer.parseInt(data.get("categoryId")));
        product.setName(data.get("name"));
        product.setPrice(Double.parseDouble(data.get("price")));
        return product;
    }

    public List<Integer> chooseProduct(Integer categoryId, Scanner scanner) {
        List<Product> productList = getProductsByCategory(categoryId);
        List<Integer> productIds = new ArrayList<>();
        List<Integer> choseProductIds;
        String input;
        System.out.println("Danh sach product:");
        System.out.println("productId, categoryId, name, price");

        for (Product product : productList) {
            productIds.add(product.getProductId());
            System.out.println(product.getProductId() + "," + categoryId + "," + product.getName() + ","
                    + product.getPrice());
        }

        while (true) {
            System.out.print("Chon cac products hop le:");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                choseProductIds = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                if (productIds.containsAll(choseProductIds)) {
                    break;
                }
            }
        }

        return choseProductIds;
    }
}
