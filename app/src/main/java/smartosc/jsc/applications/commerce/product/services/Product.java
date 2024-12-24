package smartosc.jsc.applications.commerce.product.services;

public class Product {
    public void getProductsByCategory(Integer categoryId) {
        // Simulate fetching products by category
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
        System.out.println("Fetching products for category ID: " + categoryId);
        // Example: Fetch from database or any data source
    }

    public void getProductDetails(Integer productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        System.out.println("Fetching details for product ID: " + productId);
        // Example: Fetch from database or any data source
    }

}
