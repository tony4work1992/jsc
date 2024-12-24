package smartosc.jsc.applications.ecom.modules.customers.model;

public class Customer {
    public static final String TABLE_NAME = "customers";

    public static final String CUSTOMER_ID = "customer_id";

    public static final String NAME = "name";

    public static final String EMAIL = "email";

    public static final String ADDRESS = "address";

    protected String id;

    protected String name;

    protected String email;

    protected String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
