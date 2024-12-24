package smartosc.jsc.applications.ecom.mo_nodes.cart;

import smartosc.jsc.applications.ecom.mo_nodes.cart.model.Cart;
import smartosc.jsc.applications.ecom.mo_nodes.product.model.Product;
import smartosc.jsc.applications.ecom.mo_nodes.promotion.PromotionManagement;
import smartosc.jsc.applications.ecom.mo_nodes.promotion.constant.DiscountType;
import smartosc.jsc.applications.ecom.mo_nodes.promotion.model.Promotion;

import java.util.List;

public class CartManagement {
    public Cart addProductToCart(Cart cart, List<Product> products) {
        for (Product product : products) {
            cart.getProducts().add(product);
            cart.setTotal(cart.getTotal() + product.getPrice());
        }

        return cart;
    }

    public Cart removeProductFromCart(Cart cart, List<Product> products) {
        for (Product product : products) {
            cart.getProducts().remove(product);
            cart.setTotal(cart.getTotal() - product.getPrice());
        }

        return cart;
    }

    public Cart applyDiscount(Cart cart, List<Promotion> promotions) {
        PromotionManagement promotionManagement = new PromotionManagement();

        cart.getProducts().forEach(product -> {
            promotions.forEach(promotion -> {
                if (!promotionManagement.canApply(cart, promotion)) {
                    return;
                }

                switch (promotion.getDiscountType()) {
                    case DiscountType.PERCENT:
                        product.setPrice(product.getPrice() - product.getPrice() * promotion.getDiscountAmount());
                        break;
                    case DiscountType.FLAT:
                        product.setPrice(product.getPrice() - promotion.getDiscountAmount());
                        break;
                    default:
                        break;
                }
            });
        });

        return cart;
    }
}
