package smartosc.jsc.applications.etl.utilities;

public class CompareDatatype {
    public boolean compareStrings(String actualValue, String compareValue, String operator) {
        return switch (operator) {
            case "=" ->
                actualValue.equals(compareValue);
            case ">" ->
                actualValue.compareTo(compareValue) > 0;
            case "<" ->
                actualValue.compareTo(compareValue) < 0;
            default ->
                false;
        };
    }

    public boolean compareNumbers(int actualValue, String compareValue, String operator) {
        int compareIntValue = Integer.parseInt(compareValue);
        return switch (operator) {
            case "=" ->
                actualValue == compareIntValue;
            case ">" ->
                actualValue > compareIntValue;
            case "<" ->
                actualValue < compareIntValue;
            default ->
                false;
        };
    }
}