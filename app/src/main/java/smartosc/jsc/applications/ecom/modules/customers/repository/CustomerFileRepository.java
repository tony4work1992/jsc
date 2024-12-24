package smartosc.jsc.applications.ecom.modules.customers.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.ecom.modules.customers.model.Customer;
import smartosc.jsc.applications.ecom.modules.products.model.Product;
import smartosc.jsc.applications.ecom.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerFileRepository implements CustomerRepositoryInterface {
    List<Customer> allCustomers;
    Customer currentCustomer;
    private static final String CUSTOMER_FILE = Customer.TABLE_NAME + ".json";

    @Override
    public void save(Customer customer) throws IOException {
        List<Customer> allCustomers = this.getAll();
        Map<String, Customer> allCustomersMap;
        allCustomersMap = this.getAllCustomersMap(allCustomers);

        if (allCustomersMap == null) {
            allCustomersMap = new HashMap<>();
        }

        if (customer.getId() == null) {
            String customerEmail = customer.getEmail();
            if (allCustomersMap.values().stream().anyMatch(c -> c.getEmail().equals(customerEmail))) {
                System.out.println("Customer with email " + customerEmail + " already exists");
                return;
            }
            int lastKey = allCustomersMap.isEmpty() ? 0 : Integer.parseInt(allCustomersMap.keySet().toArray()[allCustomersMap.size() - 1].toString());
            customer.setId(String.valueOf(lastKey + 1));
        }

        allCustomersMap.put(customer.getId(), customer);
        allCustomers = this.getAllCustomersList(allCustomersMap);
        ObjectMapper objectMapper = new ObjectMapper();
        FileUtils.writeToFile(CUSTOMER_FILE, objectMapper.writeValueAsString(allCustomers));
        System.out.println("Customer saved successfully");
    }

    @Override
    public Customer getByEmail(String name) throws IOException {
        List<Customer> allCustomers = this.getAll();
        return allCustomers.stream()
                .filter(customer -> customer.getName().toLowerCase().contains(name.toLowerCase()))
                .toList().getFirst();
    }

    @Override
    public List<Customer> getAll() throws IOException {
        if (this.allCustomers == null) {
            String productJsonContent = FileUtils.readFromFile(CUSTOMER_FILE);
            if (productJsonContent.isEmpty()) {
                return new ArrayList<>();
            }
            ObjectMapper objectMapper = new ObjectMapper();

            this.allCustomers = objectMapper.readValue(productJsonContent, new TypeReference<>() {
            });
        }
        return this.allCustomers;
    }

    private Map<String, Customer> getAllCustomersMap(List<Customer> allCustomers) {
        return allCustomers.stream()
                .collect(Collectors.toMap(Customer::getId, customer -> customer));
    }

    private List<Customer> getAllCustomersList(Map<String, Customer> allCustomersMap) {
        return new ArrayList<>(allCustomersMap.values());
    }
}
