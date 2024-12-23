package smartosc.jec.ecommerce.promotion.service;

import smartosc.jec.ecommerce.promotion.model.Promotion;

public interface IPromotionService {
    void addPromotion(Promotion promotion);
    Promotion getPromotionByCode(String promoCode);
}
