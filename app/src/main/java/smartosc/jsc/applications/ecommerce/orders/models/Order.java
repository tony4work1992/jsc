package smartosc.jsc.applications.ecommerce.orders.models;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private final int id;
    private String customerEmail;
    private String customerName;
    private String address;
    private String phoneNumber;
//    private ArrayList<Integer> productIds;
    private String orderDetail;
    private int promotionId;
    private String status;
    private double totalPrice;

    public static final String PENDING = "pending";
    public static final String ON_DELIVERY = "on_delivery";
    public static final String COMPLETED = "completed";
    public static final String CANCELLED = "cancelled";
    public static final String REFUND = "refund";

    public Order(int id, String customerEmail, String customerName, String address, String phoneNumber, String orderDetail, int promotionId, String status, double totalPrice){
        this.id = id;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderDetail = orderDetail;
//        this.productIds = productIds;
        this.promotionId = promotionId;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

//    public ArrayList<Integer> getProductIds() {
//        return productIds;
//    }
//
//    public void setProductIds(ArrayList<Integer> productIds) {
//        this.productIds = productIds;
//    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Customer Email: " + customerEmail + ", Customer Name: " + customerName  + ", Address: " + address + ", Phone Number: " + phoneNumber + ", orderDetail: " + orderDetail + ", promotionId: "+ promotionId + ", status: " + status + ", totalPrice: " + totalPrice;
    }

    public String toCsv() {
        return id + "," + customerEmail + "," + customerName + "," + address + "," + phoneNumber + "," + orderDetail + "," + promotionId + "," + status + "," + totalPrice;
    }
}
