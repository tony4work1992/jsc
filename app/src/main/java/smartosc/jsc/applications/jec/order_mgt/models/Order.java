package smartosc.jsc.applications.jec.order_mgt.models;

import java.util.List;

import lombok.Data;
import smartosc.jsc.applications.jec.product_mgt.models.Product;

@Data
public class Order {
    private Integer orderId;
    private String orderStatus;
    private List<Product> products;
    private double amount;
    private Integer userId;

}