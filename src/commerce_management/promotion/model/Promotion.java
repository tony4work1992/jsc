package commerce_management.promotion.model;

public class Promotion {
    private String type; // "PERCENTAGE" or "FLAT"
    private double value;
    private double condition;

    public Promotion(String type, double value, double condition) {
        this.type = type;
        this.value = value;
        this.condition = condition;
    }

    public double apply(double totalPrice) {
        if (totalPrice < condition) {
            return totalPrice; // No discount applied
        }
        if (type.equalsIgnoreCase("PERCENTAGE")) {
            return totalPrice - (totalPrice * value / 100);
        } else if (type.equalsIgnoreCase("FLAT")) {
            return totalPrice - value;
        }
        return totalPrice;
    }
}
