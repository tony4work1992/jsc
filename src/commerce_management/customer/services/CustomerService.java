package commerce_management.customer.services;

import commerce_management.customer.model.Customer;
import commerce_management.product.model.Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerService {
    private static final String FILE_NAME = "src/data/customers.txt";
    private List<Customer> customers;

    public CustomerService() {
        File file = new File(FILE_NAME);
        try {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error initializing customers file: " + e.getMessage());
        }
        this.customers = new ArrayList<>();
        loadCustomers();
    }

    public Customer getCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getUserId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    public void addCustomer(Customer customer) {
        if (isCustomerIdExists(customer.getUserId())) {
            System.out.println("Error: Customer with UserID " + customer.getUserId() + " already exists.");
            return;
        }
        customers.add(customer);
        saveCustomers();
    }

    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private boolean isCustomerIdExists(int userId) {
        for (Customer customer : customers) {
            if (customer.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }

    private void saveCustomers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Customer customer : customers) {
                writer.write(customer.getUserId() + "," + customer.getFullName() + "," + customer.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    public void updateCustomer(int id, String name, String email) {
        for (Customer customer : customers) {
            if (customer.getUserId() == id) {
                customer.setFullName(name);
                customer.setEmail(email);
                saveCustomers();
                System.out.println("Customer updated successfully.");
                return;
            }
        }
        System.out.println("Error: Customer with ID " + id + " not found.");
    }

    public void deleteCustomer(int id) {
        Iterator<Customer> iterator = customers.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getUserId() == id) {
                iterator.remove();
                saveCustomers();
                System.out.println("Customer deleted successfully.");
                return;
            }
        }
        System.out.println("Error: Customer with ID " + id + " not found.");
    }


    private void loadCustomers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int userId = Integer.parseInt(parts[0]);
                String fullName = parts[1];
                String email = parts[2];
                customers.add(new Customer(userId, fullName, email));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Customer file not found. Starting with an empty customer list.");
        } catch (IOException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
    }
}
