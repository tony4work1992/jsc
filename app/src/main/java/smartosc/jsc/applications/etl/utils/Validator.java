package smartosc.jsc.applications.etl.utils;

import java.util.ArrayList;

public class Validator {

    public static void validateColName(String colName, ArrayList<String> data) {
        if (!data.contains(colName)) {
            throw new IllegalArgumentException("Column " + colName + " does not exist in the data set");
        }
    }
}
