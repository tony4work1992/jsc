package smartosc.jsc.applications.etl.utils;

public class InputTypeResolver {

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
