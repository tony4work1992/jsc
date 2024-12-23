package services;

import entitites.Promotion;

import java.util.List;
import java.util.Optional;

public interface IPromotionService {
    public void addPromotion(Promotion promotion);

    public List<Promotion> listPromotions();

    public Optional<Promotion> findPromotionById(String id);
}
