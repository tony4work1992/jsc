package smartosc.jsc.applications.ecom.mo_nodes.customer;

import smartosc.jsc.applications.ecom.mo_nodes.customer.model.Customer;

public class CustomerManagement {
    public Customer login(int id) {
        return new Customer(id);
    }

    public Customer register(int id, String fullName, String email) {
        Customer customer = new Customer(id);
        customer.setFullName(fullName);
        customer.setEmail(email);

        return customer;
    }
}
