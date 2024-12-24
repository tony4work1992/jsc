package smartosc.jsc.applications.ecom.modules.orders.model;

public class Order{
    public static final String TABLE_NAME = "orders";

    public static final String ENTITY_ID = "order_id";

    protected String customerId;

    protected String totalPrice;

    protected String status;

    protected String promotionIds;

    public Order() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPromotionIds() {
        return promotionIds;
    }

    public void setPromotionIds(String promotionIds) {
        this.promotionIds = promotionIds;
    }
}
