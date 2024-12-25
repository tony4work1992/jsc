package smartosc.jsc.applications.ecom.modules.orders.model;

public class Order{
    public static final String TABLE_NAME = "orders";

    public static final String ENTITY_ID = "order_id";

    protected String id;
    protected String customerEmail;

    protected String totalPrice;

    protected String status;

    protected String promotionIds;

    public Order(String customerEmail, String status, String totalPrice) {
        this.customerEmail = customerEmail;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
