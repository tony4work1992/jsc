package smartosc.jsc.applications.etl.mo_filter_columns;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.constants.AppConstants;
import smartosc.jsc.applications.etl.mo_filter_columns.params.FilterColumnsParamsExtractor;
import smartosc.jsc.applications.etl.mo_filter_columns.params.ColumnModel;

public class FilterColumnsExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        FilterColumnsParamsExtractor extractor = new FilterColumnsParamsExtractor();
        List<ColumnModel> filterParams = extractor.extractParams(params);
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode newData = JsonNodeFactory.instance.arrayNode();
        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (JsonNode item : returnData) {
            Boolean result = true;
            for (ColumnModel param : filterParams) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                if (!this.filter(item, param)) {
                    result = false;
                    break;
                }
            }
            if (result) {
                newData.add(item);
            }
        }
        return newData;

    }

    public Boolean filter(JsonNode data, ColumnModel param) {
        String column = param.getColumn();
        String comparisonOperator = param.getComparisonOperator();
        String comparisonValue = param.getComparisonValue();
        if (!data.has(column) || data.get(column).isNull()) {
            throw new RuntimeException("Filter Columns: Column " + column + " does not exists");
        }

        JsonNode columnValue = data.get(column);

        if (columnValue.isInt()) {
            return this.getCompareResultForInt(columnValue.asInt(), comparisonOperator, comparisonValue);
        } else if (columnValue.isTextual()) {
            return this.getCompareResultForString(columnValue.asText(), comparisonOperator, comparisonValue);
        }
        return false;
    }

    private Boolean getCompareResultForInt(Integer columnValue, String comparisonOperator,
            String comparisonValue) {
        boolean conditionMet = false;
        Integer comparisonValueInt = Integer.valueOf(comparisonValue);
        switch (comparisonOperator) {
            case AppConstants.GREATER ->
                conditionMet = columnValue > comparisonValueInt;
            case AppConstants.EQUAL -> conditionMet = Objects.equals(columnValue, comparisonValueInt);
            case AppConstants.LESS ->
                conditionMet = columnValue < comparisonValueInt;
        }

        return conditionMet;
    }

    private Boolean getCompareResultForString(String columnValue, String comparisonOperator,
            String comparisonValue) {
        boolean conditionMet = false;
        switch (comparisonOperator) {
            case AppConstants.GREATER -> conditionMet = columnValue.compareTo(comparisonValue) > 0;
            case AppConstants.EQUAL -> conditionMet = columnValue.compareTo(comparisonValue) == 0;
            case AppConstants.LESS -> conditionMet = columnValue.compareTo(comparisonValue) < 0;
        }

        return conditionMet;
    }

}
