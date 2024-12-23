package entitites;

import enums.PromotionType;

import java.io.Serializable;

public class Promotion implements Serializable {
    private String id;
    private String type; // Percentage, Flat
    private double value;
    private double condition;
    private String code;
    private PromotionType promotionType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Promotion(){

    }
    public Promotion(String id, String type, double value, double condition, PromotionType promotionType) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.condition = condition;
        this.promotionType = promotionType;
    }

    // Constructors, getters, and setters
    public Promotion(String id, String type, double value, double condition) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return String.format("Promotion ID: %s, Type: %s, Value: %.2f, Condition: %.2f", id, type, value, condition);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getCondition() {
        return condition;
    }

    public void setCondition(double condition) {
        this.condition = condition;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }
    // Getters and setters omitted for brevity
}
