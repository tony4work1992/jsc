package smartosc.jsc.applications.ecom.mo_nodes.product.model;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private int qty;

    public Product(Integer id, String name, String description, double price, int qty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + description + "," + price + "," + qty;
    }
}
