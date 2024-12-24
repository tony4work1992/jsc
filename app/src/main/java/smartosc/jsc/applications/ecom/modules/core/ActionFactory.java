package smartosc.jsc.applications.ecom.modules.core;

import smartosc.jsc.applications.ecom.modules.customers.cli.CustomerAction;
import smartosc.jsc.applications.ecom.modules.products.cli.ProductAction;

public class ActionFactory {
    public static AbstractAction getAction(String actionName) {
        return switch (actionName) {
            case "1" -> new CustomerAction();
            case "2" -> new ProductAction();
            default -> throw new RuntimeException("Action not found");
        };
    }
}
