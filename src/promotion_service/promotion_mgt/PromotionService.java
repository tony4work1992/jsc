package promotion_service.promotion_mgt;

import java.util.Map;
import promotion_service.promotion_mgt.constants.PromotionType;
import promotion_service.promotion_mgt.model.Promotion;
import promotion_service.promotion_mgt.repository.PromotionRepository;

public class PromotionService {
    
    PromotionRepository promotionRepository = new PromotionRepository();
    
    public void viewPromotions() {
        System.out.println("\n--- Promotion List ---");
        Map<Integer, Promotion> promotions = promotionRepository.getListPromotion();
        for (Promotion promotion : promotions.values()) {
            System.out.println("Promotion[id=" + promotion.getId() + ",name=" + promotion.getName() + ",type=" + promotion.getType() + ", value=" + promotion.getValue() + ", minOrderValue=" + promotion.getMinOrderValue() + "]");
        }
    }

     public boolean isApplicable(int promotionId, double total) {
        Promotion promotion = promotionRepository.getPromotionById(promotionId);
        return total >= promotion.getMinOrderValue();
     }

     public double apply(int promotionId, double total) {
         Promotion promotion = promotionRepository.getPromotionById(promotionId);
         String type = promotion.getType();
         Double value = promotion.getValue();
         if (type.equals(PromotionType.TYPE_PERCENTAGE)) {
             return total - (total * (value / 100));
         } else if (type.equals(PromotionType.TYPE_FLAT)) {
             return total - value;
         }
         return total;
     }
    
    public String getPromotionNameById(int promotionId)
    {
        Promotion promotion = promotionRepository.getPromotionById(promotionId);
        
        return promotion.getName();
    }
}
