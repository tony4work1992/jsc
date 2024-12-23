package smartosc.jsc.applications.ecom.modules.products.model;

import smartosc.jsc.applications.ecom.modules.core.Identifiable;

public class Product implements Identifiable {
    public static final String TABLE_NAME = "products";

    public static final String PRODUCT_ID = "ID";

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";



    protected String id;
    protected String name;
    protected String description;
    protected String price;
    protected String quantity;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
