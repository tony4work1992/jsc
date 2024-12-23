package smartosc.jsc.applications.ecom.modules.customers;

public class Customer {
    public static final String TABLE_NAME = "customers";

    public static final String ENTITY_ID = "customer_id";

    protected String name;

    protected String email;

    protected String address;

    public Customer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
