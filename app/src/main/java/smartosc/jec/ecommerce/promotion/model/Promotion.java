package smartosc.jec.ecommerce.promotion.model;

public abstract class Promotion {
    protected String promoCode;

    public Promotion(String promoCode) {
        this.promoCode = promoCode;
    }

    public abstract double applyDiscount(double amount);

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
}
