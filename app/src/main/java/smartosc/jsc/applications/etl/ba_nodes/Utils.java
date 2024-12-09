package smartosc.jsc.applications.etl.ba_nodes;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

public final class Utils {

    private Utils() {
        throw new AssertionError("Cannot instantiate Utils class");
    }

    public static <T extends Comparable<T>> Boolean compareValue(T value, T compareValue, String operator) {
        if (value.toString().isEmpty() || compareValue.toString().isEmpty() || operator.isEmpty()) {
            throw new IllegalArgumentException("Value and compareValue cannot be null.");
        }
        return switch (operator) {
            case Constants.OPERATOR_EQUALS -> value.equals(compareValue);
            case Constants.OPERATOR_GREATER_THAN -> value.compareTo(compareValue) > 0;
            case Constants.OPERATOR_LESS_THAN -> value.compareTo(compareValue) < 0;
            case Constants.OPERATOR_LIKE -> ((String) value).matches(".*" + compareValue + ".*");
            default -> false;
        };
    }
}