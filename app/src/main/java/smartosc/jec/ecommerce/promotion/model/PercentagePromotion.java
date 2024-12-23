package smartosc.jec.ecommerce.promotion.model;

public class PercentagePromotion extends Promotion {
    private double percentage;

    public PercentagePromotion(String promoCode, double percentage) {
        super(promoCode);
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double amount) {
        return amount * (1 - percentage / 100);
    }
}
