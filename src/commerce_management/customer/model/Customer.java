package commerce_management.customer.model;

public class Customer {
    public Integer userId;
    public String fullName;
    public String email;

    public Customer(int userId, String fullName, String email) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Customer setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public Customer setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }
}
