package smartosc.jec.ecommerce.promotion.model;

public class PromotionFactory {
    public static Promotion createPromotion(String promoCode, String type, double value) {
        switch (type) {
            case "percentage":
                return new PercentagePromotion(promoCode, value);
            case "flat":
                return new FlatPromotion(promoCode, value);
            default:
                throw new IllegalArgumentException("Unknown promotion type");
        }
    }
}

