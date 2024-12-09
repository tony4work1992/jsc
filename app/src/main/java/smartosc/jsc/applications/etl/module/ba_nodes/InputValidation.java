package smartosc.jsc.applications.etl.module.ba_nodes;

import java.util.InputMismatchException;

import com.fasterxml.jackson.databind.JsonNode;

public class InputValidation {
/**
     * Validate dataset contains the necessary columns or not.
     */
    public void validateColumnsExist(String moduleName, String[] requiredColumns, JsonNode data) {
        for (String column : requiredColumns) {
            if (!data.has(column)) {
                throw new InputMismatchException("Error in module " +moduleName+ ": Column " + column + " does not exist in the dataset.");
            }
        }
    }

    /**
     * Validate input cannot be null.
     */
    public void validateInputNotNull(String moduleName, String input) {
        if (input == null || input.equals("")) {
            throw new InputMismatchException("Error in module " +moduleName+ ": Input cannot be null.");
        }
    }

    /**
     * Validate input cannot be empty.
     */
    public void validateInputNotEmpty(String moduleName, String[] input) {
        if (input.length == 0) {
            throw new InputMismatchException("Error in module " +moduleName+ ": Input cannot be empty.");
        }
    }
}
