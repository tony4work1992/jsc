package smartosc.jec.ecommerce.promotion.model;

public class FlatPromotion extends Promotion {
    private double discountValue;

    public FlatPromotion(String promoCode, double discountValue) {
        super(promoCode);
        this.discountValue = discountValue;
    }

    @Override
    public double applyDiscount(double amount) {
        return amount - discountValue;
    }
}

