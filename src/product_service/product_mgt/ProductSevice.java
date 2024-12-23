package product_service.product_mgt;

import java.util.Scanner;
import product_service.product_mgt.model.Product;
import product_service.product_mgt.repository.ProductRepository;

public class ProductSevice {
    ProductRepository productRepository = new ProductRepository();

    public void addProduct() {
        Scanner scanner = new Scanner(System.in);
        // scanner.nextLine(); // Consume newline
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Stock: ");
        int stockQty = scanner.nextInt();

        int id = productRepository.getLastRealProductId() + 1;
        Product product = new Product(id, name, description, price, stockQty);
        productRepository.addProduct(product);
    }

    public void viewProducts() {
        System.out.println("\n--- Product List ---");
        for (Product product : productRepository.getListProduct().values()) {
            System.out.println(product.getId() + " - " + product.getName() + " - " + product.getDescription() + " - $" + product.getPrice() + " - Stock Qty: " + product.getStockQty());
        }
    }

    public String getProductNameById(int productId)
    {
        Product product =  productRepository.getProductById(productId);

        return product.getName();
    }

    public Double addProductToCart(int productId)
    {
        Product product = productRepository.getProductById(productId);
        double total = 0;
        if (product.getStockQty() > 0) {
            total += product.getPrice();
            product.setStockQty(product.getStockQty() - 1);
            productRepository.updateProduct(product.getId(), product);
        } else {
            System.out.println("Product out of stock.");
        }

        return total;
    }
}
