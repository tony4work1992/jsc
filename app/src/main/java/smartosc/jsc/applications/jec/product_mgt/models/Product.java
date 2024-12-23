package smartosc.jsc.applications.jec.product_mgt.models;

import lombok.Data;

@Data
public class Product {
    private Integer productId;
    private Integer categoryId;
    private String name;
    private double price;

    public Product(Integer productId) {
        this.productId = productId;
    }
}