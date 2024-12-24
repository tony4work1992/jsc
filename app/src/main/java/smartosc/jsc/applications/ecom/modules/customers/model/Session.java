package smartosc.jsc.applications.ecom.modules.customers.model;

import java.util.HashMap;

public class Session {
    private static Session customerSession;
    private HashMap<String, String> data;

    private Session() {
        data = new HashMap<>();
    }

    public static Session getCustomerSession() {
        if (customerSession == null) {
            customerSession = new Session();
        }
        return customerSession;
    }

    public void set(String key, String value) {
        data.put(key, value);
    }

    public String get(String key) {
        return data.get(key);
    }
}
