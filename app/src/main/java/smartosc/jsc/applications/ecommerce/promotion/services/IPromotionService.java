package smartosc.jsc.applications.ecommerce.promotion.services;

import smartosc.jsc.applications.ecommerce.promotion.models.Promotion;

public interface IPromotionService {
    void managePromotions();
    void createPromotion();
    void updatePromotion();
    void getListPromotion();
    void deletePromotionById();
    Promotion getPromotionById(int id);
}
