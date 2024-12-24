package smartosc.jsc.applications.ecom.mo_nodes.promotion.model;

import lombok.Data;

import java.util.List;

@Data
public class Promotion {
    private Integer promotionId;
    private String name;
    private String discountType;
    private double discountAmount;
    private List<Condition> condition;

    public Promotion(Integer promotionId, String name, String discountType, double discount, List<Condition> conditions) {
        this.promotionId = promotionId;
        this.name = name;
        this.discountType = discountType;
        this.discountAmount = discount;
        this.condition = conditions;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discount) {
        this.discountAmount = discount;
    }

    public List<Condition> getCondition() {
        return condition;
    }

    public void setCondition(List<Condition> conditions) {
        this.condition = conditions;
    }

    public String toString() {
        return promotionId + " " + name + " " + discountType + " " + discountAmount;
    }
}
