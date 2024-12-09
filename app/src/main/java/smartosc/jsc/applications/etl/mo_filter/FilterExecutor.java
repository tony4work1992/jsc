package smartosc.jsc.applications.etl.mo_filter;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_filter.params.FilterModel;
import smartosc.jsc.applications.etl.mo_filter.params.FilterParamsExtractor;
import smartosc.jsc.applications.etl.utilities.CompareDatatype;

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

        CompareDatatype compareDatatype = new CompareDatatype();
        return switch (valueNode.getNodeType()) {
            case STRING ->
                compareDatatype.compareStrings(valueNode.asText(), compareValue, operator);
            case NUMBER ->
                compareDatatype.compareNumbers(valueNode.asInt(), compareValue, operator);
            default ->
                false;
        };
    }
}
