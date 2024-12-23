import entitites.Promotion;
import enums.PromotionType;
import services.IPromotionService;
import services.impls.PromotionServiceImpl;

import java.util.List;
import java.util.Optional;

public class PromotionApplication {
    public static void main(String[] args) {
        IPromotionService promotionService = new PromotionServiceImpl();

        // Tạo một chương trình khuyến mãi mới
        Promotion promotion1 = new Promotion();
        promotion1.setId("P001");
        promotion1.setType("Percentage");
        promotion1.setValue(20.0);
        promotion1.setCondition(100.0);
        promotion1.setCode("PROMO20");
        promotion1.setPromotionType(PromotionType.PERCENTAGE);

        // Thêm khuyến mãi vào danh sách và vào file
        promotionService.addPromotion(promotion1);

        // Hiển thị tất cả các khuyến mãi
        List<Promotion> promotions = promotionService.listPromotions();
        System.out.println("Danh sách khuyến mãi:");
        promotions.forEach(promotion -> System.out.println(promotion.getId() + ": " + promotion.getCode()));

        // Tìm kiếm khuyến mãi theo ID
        Optional<Promotion> promotionFound = promotionService.findPromotionById("P001");
        promotionFound.ifPresent(promotion -> {
            System.out.println("Khuyến mãi tìm thấy: " + promotion.getCode() + " - " + promotion.getType());
        });
    }
}
