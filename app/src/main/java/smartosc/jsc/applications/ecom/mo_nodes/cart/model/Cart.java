package smartosc.jsc.applications.ecom.mo_nodes.cart.model;

import lombok.Data;
import smartosc.jsc.applications.ecom.mo_nodes.product.model.Product;

import java.util.List;

@Data
public class Cart {
    private List<Product> products = null;
    private double total = 0.0;
    private Integer customerId;
    private Integer qty = 0;

    public Cart(Integer customerId) {
        this.customerId = customerId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
