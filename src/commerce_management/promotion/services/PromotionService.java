package commerce_management.promotion.services;

import java.util.List;

public class PromotionService {
    public void getPromotions(Integer categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Show ID cannot be null");
        }
        System.out.println("Fetching promotions for show ID: " + categoryId);
        // Example: Fetch promotions for a specific show or event
    }

    public void applyPromotions(List<Integer> promotionIds) {
        System.out.println("Applied promotions");
    }

}
