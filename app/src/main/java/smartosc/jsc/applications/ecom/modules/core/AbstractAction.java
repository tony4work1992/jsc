package smartosc.jsc.applications.ecom.modules.core;

import smartosc.jsc.applications.ecom.modules.customers.model.Session;

import java.io.IOException;

public abstract class AbstractAction {
    protected Boolean keepRunning = true;

    public abstract void execute() throws IOException;

    protected void printMenu() {
        System.out.println("Hi " + Session.getCustomerSession().get("name") + "!");
    }
}
