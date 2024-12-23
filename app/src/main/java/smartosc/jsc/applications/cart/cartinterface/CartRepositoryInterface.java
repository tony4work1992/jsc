/*
 * This source file was generated by the Gradle 'init' task
 */
package smartosc.jsc.applications.cart.cartinterface;

import java.util.List;

import smartosc.jsc.applications.cart.Cart;
import smartosc.jsc.applications.cart.cartitems.CartItem;

public interface CartRepositoryInterface {
    void addProductToCart(String productId, int quantity, double price);
    Cart getCartById(int cartId);
    List<CartItem> getCartItems(int cartId);
    // void addPromotionToCart(String promotionCode);
}