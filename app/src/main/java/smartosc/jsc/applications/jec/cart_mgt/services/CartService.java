package smartosc.jsc.applications.jec.cart_mgt.services;

import java.util.ArrayList;
import java.util.List;

import smartosc.jsc.applications.jec.cart_mgt.models.Cart;
import smartosc.jsc.applications.jec.product_mgt.models.Product;
import smartosc.jsc.applications.jec.product_mgt.services.ProductService;
import smartosc.jsc.applications.jec.promotion_mgt.models.Promotion;
import smartosc.jsc.applications.jec.promotion_mgt.services.PromotionService;

public class CartService {

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

        ProductService productService = new ProductService();

        List<Product> products = productIds.stream().map((productId) -> productService.getProductDetails(productId))
                .toList();
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

    public Cart applyPromotions(Cart cart, List<Integer> promotionIds) {
        PromotionService promoService = new PromotionService();
        List<Integer> promoIdsInCart = new ArrayList<>();
        double amount = cart.getAmount();
        System.out.println("Amount before promotion: " + amount);

        for (Integer promotionId : promotionIds) {
            Promotion promotion = promoService.getPromotionDetail(promotionId);
            if (promotion.getDiscountType().equals(Promotion.FLAT_DISCOUNT)) {
                promoIdsInCart.add(promotionId);
                amount -= promotion.getDiscountAmount();
            }

            if (promotion.getDiscountType().equals(Promotion.BY_PERCENT)) {
                promoIdsInCart.add(promotionId);
                amount -= amount * promotion.getDiscountAmount() / 100;
            }
        }
        amount = Math.round(amount * 100.0) / 100.0;
        cart.setAmount(amount);
        cart.setPromotionIds(promoIdsInCart);
        System.out.println("Applied promotions");
        System.out.println("Amount after promotions: " + amount);
        return cart;
    }
}
