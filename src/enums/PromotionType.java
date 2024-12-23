package enums;

public enum PromotionType {
    PERCENTAGE("Percentage"),
    FLAT("Flat");

    private final String type;

    PromotionType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}