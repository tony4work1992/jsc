package smartosc.jsc.applications.etl.modules.mo_filter.processcor;

import smartosc.jsc.applications.etl.modules.base.DataSet;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;
import smartosc.jsc.applications.etl.modules.mo_filter.constants.SupportOperation;
import smartosc.jsc.applications.etl.modules.mo_filter.instruction.FilterInstruction;
import smartosc.jsc.applications.etl.utils.InputTypeResolver;
import smartosc.jsc.applications.etl.utils.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilterProcessor implements CsvProcessorInterface {

    public DataSet execute(AbstractInstruction instruction, DataSet dataSet) {
        // Get the filter key and value from the instructions.
        FilterInstruction filterInstruction = (FilterInstruction) instruction;
        String columnName = filterInstruction.getColumnName();
        String value = filterInstruction.getValue();
        String operation = filterInstruction.getOperation();
        if (operation == null) {
            operation = SupportOperation.EQUALS;
        }
        ArrayList<String> headers = dataSet.getHeaders();

        Validator.validateColName(columnName, headers);

        // Filter data based on key and value and operation.
        return this.filterByOperation(dataSet, columnName, value, operation);
    }

    private DataSet filterByOperation(DataSet dataSet, String columnName, String value, String operation) {
        List<Map<String, String>> filteredDataSet = dataSet.getData().stream()
                .filter(map -> {
                    String mapValue = map.get(columnName);
                    return this.isValid(mapValue, value, operation);
                })
                .collect(Collectors.toList());

        return new DataSet(
                filteredDataSet,
                dataSet.getHeaders()
        );
    }

    private boolean isValid(String mapValue, String value, String operation) {
        return switch (operation) {
            case SupportOperation.EQUALS -> this.isEqual(mapValue, value);
            case SupportOperation.NOT_EQUALS -> this.isNotEqual(mapValue, value);
            // TO DO: add more operations
            default -> throw new IllegalArgumentException("Invalid operation: " + operation);
        };
    }

    private boolean isEqual(String mapValue, String value) {
        return InputTypeResolver.isInteger(value) ? Integer.parseInt(value) == Integer.parseInt(mapValue) : value.equals(mapValue);
    }

    private boolean isNotEqual(String mapValue, String value) {
        return InputTypeResolver.isInteger(value) ? Integer.parseInt(value) != Integer.parseInt(mapValue) : !value.equals(mapValue);
    }
}