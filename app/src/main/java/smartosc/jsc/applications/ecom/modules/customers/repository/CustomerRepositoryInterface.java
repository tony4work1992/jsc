package smartosc.jsc.applications.ecom.modules.customers.repository;

import smartosc.jsc.applications.ecom.modules.customers.model.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerRepositoryInterface {
    void save(Customer customer) throws IOException;
    Customer getByEmail(String email) throws IOException;
    List<Customer> getAll() throws IOException;
}
