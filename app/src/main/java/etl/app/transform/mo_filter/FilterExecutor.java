package etl.app.transform.mo_filter;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import etl.app.transform.ba_nodes.Executable;
import etl.app.transform.mo_filter.params.FilterModel;
import etl.app.transform.mo_filter.params.FilterParamsExtractor;

public class FilterExecutor implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {

        FilterParamsExtractor extractor = new FilterParamsExtractor();
        List<FilterModel> filterModels = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();
        if (!returnData.isArray()) {
            throw new RuntimeException("[FilterExecutor] Data is not an array");
        }

        ObjectMapper mapper = new ObjectMapper();

        ArrayNode filteredData = mapper.createArrayNode();
        data.forEach(item -> {
            if (isItemValid(filterModels, item)) {
                filteredData.add(item);
            }
        });

        return filteredData;
    }

    private boolean isItemValid(List<FilterModel> filterConditions, JsonNode item) {
        return filterConditions.stream().allMatch(condition -> matchesCondition(condition, item));
    }

    private boolean matchesCondition(FilterModel condition, JsonNode item) {
        JsonNode valueNode = item.get(condition.getFilterColumn());
        if (valueNode == null || valueNode.isNull()) {
            return false;
        }

        String operator = condition.getOperator();
        String compareValue = condition.getFilterValue();

        return switch (valueNode.getNodeType()) {
            case STRING ->
                compareStrings(valueNode.asText(), compareValue, operator);
            case NUMBER ->
                compareNumbers(valueNode.asInt(), compareValue, operator);
            default ->
                false;
        };
    }

    private boolean compareStrings(String actualValue, String compareValue, String operator) {
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

    private boolean compareNumbers(int actualValue, String compareValue, String operator) {
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
