package smartosc.jsc.applications.jec.promotion_mgt.models;

import lombok.Data;

@Data
public class Promotion {

    public static final String BY_PERCENT = "by_percent";
    public static final String FLAT_DISCOUNT = "flat_discount";

    public Integer promotionId;
    public Integer categoryId;
    public String discountType;
    public double discountAmount;
    
}
