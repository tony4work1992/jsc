package smartosc.jsc.applications.ecom.modules.customers.cli;

import smartosc.jsc.applications.ecom.modules.core.AbstractAction;
import smartosc.jsc.applications.ecom.modules.customers.model.Session;

import java.io.IOException;

import static smartosc.jsc.applications.ecom.modules.core.MainMenu.scanner;


public class CustomerAction extends AbstractAction {

    @Override
    public void execute() throws IOException {
        Session customerSession = Session.getCustomerSession();
        // Enter you name
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        customerSession.set("name", name);
        // Enter your email
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        customerSession.set("email", email);
        // Enter your address
        System.out.println("Enter your address: ");
        String address = scanner.nextLine();
        customerSession.set("address", address);
    }
}
