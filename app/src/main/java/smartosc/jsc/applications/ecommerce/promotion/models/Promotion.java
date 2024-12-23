package smartosc.jsc.applications.ecommerce.promotion.models;

public class Promotion {
    private final int id;
    private String name;
    private String condition;
    private double discount;
    private int type;

    public Promotion(int id, String name, String condition, int type, double discount) {
        this.id = id;
        this.name = name;
        this.condition = condition;
        this.type = type;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Condition: " + condition  + ", Type: " + type + ", Discount: " + discount;
    }

    public String toCsv() {
        return id + "," + name + "," + condition + "," + type + "," + discount;
    }
}
