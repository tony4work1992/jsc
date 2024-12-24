package smartosc.jsc.applications.commerce.customer.model;

public class Customer {
    public Integer userId;
    public String fullname;
    public String email;

    public Customer(Integer customerId) {
        this.userId = customerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setCustomerId(Integer customerId) {
        this.userId = customerId;
    }

    public void setCustomerFullName(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
