package smartosc.jsc.applications.ecom.modules.customers.cli;

import smartosc.jsc.applications.ecom.modules.core.AbstractAction;
import smartosc.jsc.applications.ecom.modules.customers.model.Customer;
import smartosc.jsc.applications.ecom.modules.customers.repository.CustomerFileRepository;
import smartosc.jsc.applications.ecom.modules.customers.repository.CustomerRepositoryInterface;

import java.io.IOException;

import static smartosc.jsc.applications.ecom.modules.core.MainMenu.scanner;


public class CustomerAction extends AbstractAction {

    @Override
    public void execute() throws IOException {
        // Enter you name
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        // Enter your email
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        // Enter your address
        System.out.println("Enter your address: ");
        String address = scanner.nextLine();
        Customer customer = new Customer();
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setName(name);


    }
}
