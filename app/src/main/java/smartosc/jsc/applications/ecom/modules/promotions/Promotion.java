package smartosc.jsc.applications.ecom.modules.promotions;

public class Promotion{
    public static final String TABLE_NAME = "promotions";

    public static final String ENTITY_ID = "promotion_id";

    protected String name;

    protected String description;

    protected String discountType;

    protected String discount;

    public Promotion() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
