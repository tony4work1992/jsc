package smartosc.jsc.applications.commerce.cart.services;

import java.util.List;

import smartosc.jsc.applications.commerce.product.model.Product;
import smartosc.jsc.applications.commerce.cart.model.Cart;

public class Cart {
    public Cart addToCart(List<Integer> productIds, Integer userId) throws RuntimeException {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (productIds == null || productIds.isEmpty()) {
            throw new RuntimeException("Product list cannot be null or empty");
        }

        List<Integer> nulls = productIds.stream().filter((productId) -> productId == null).toList();
        if (!nulls.isEmpty()) {
            throw new RuntimeException("Product IDs contain null value");
        }

        List<Product> products = productIds.stream().map((productId) -> new Product(productId)).toList();
        double amount = products.stream().mapToDouble(Product::getPrice).sum();

        Cart cart = new Cart(products, amount, userId);
        System.out.println("Products added to cart for user ID: " + userId);
        return cart;
    }

    public void removeFromCart(List<Integer> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            throw new IllegalArgumentException("Product list cannot be null or empty");
        }
        System.out.println("Removing products from cart: " + productIds);
        // Example: Update cart by removing these products
    }

}
