package promotion_service.promotion_mgt.model;

public class Promotion {
    private Integer id;
    private String name;
    private String type; // "PERCENTAGE" or "FLAT"
    private double value;
    private double minOrderValue;
    
    public Promotion(Integer id, String name, String type, double value, double minOrderValue) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.minOrderValue = minOrderValue;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public double getMinOrderValue() {
        return minOrderValue;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setMinOrderValue(double minOrderValue) {
        this.minOrderValue = minOrderValue;
    }
}
