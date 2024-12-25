package smartosc.jsc.applications.ecom.modules.core;

import smartosc.jsc.applications.ecom.modules.orders.cli.OrderAction;
import smartosc.jsc.applications.ecom.modules.products.cli.ProductAction;

public class ActionFactory {
    public static AbstractAction getAction(String actionName) {
        return switch (actionName) {
            case "1" -> new ProductAction();
            case "2" -> new OrderAction();
            default -> throw new RuntimeException("Action not found");
        };
    }
}
