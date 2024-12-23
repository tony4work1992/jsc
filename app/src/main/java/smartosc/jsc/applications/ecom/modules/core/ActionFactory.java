package smartosc.jsc.applications.ecom.modules.core;

import smartosc.jsc.applications.ecom.modules.products.cli.ProductAction;

public class ActionFactory {
    public static AbstractAction getAction(String actionName) {
        if (actionName.equals("1")) {
            return new ProductAction();
        }
        throw new RuntimeException("Action not found");
    }
}
