package smartosc.jsc.applications.etl.ba_nodes;

import smartosc.jsc.applications.etl.constant.Constants;

public final class Utils {

    private Utils() {
        throw new AssertionError("Cannot instantiate Utils class");
    }

    /**
     * Checks if a string is null or empty.
     *
     * @param str The string to check.
     * @return True if the string is null or empty, false otherwise.
     */
    public static <T extends Comparable<T>> Boolean compareValue(T value, T compareValue, String operator) {
        if (value == null || compareValue == null || operator == null) {
            throw new IllegalArgumentException("Value and compareValue cannot be null.");
        }
        return switch (operator) {
            case Constants.OPERATOR_EQUALS -> value.equals(compareValue);
            case Constants.OPERATOR_GREATER_THAN -> value.compareTo(compareValue) > 0;
            case Constants.OPERATOR_LESS_THAN -> value.compareTo(compareValue) < 0;
            case Constants.OPERATOR_CONTAINS -> ((String) value).contains((String) compareValue);
            case Constants.OPERATOR_NOT_CONTAINS -> !((String) value).contains((String) compareValue);
            default -> false;
        };
    }
}
