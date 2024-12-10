package smartosc.jsc.applications.etl.mo_filter_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.ba_nodes.Utils;
import smartosc.jsc.applications.etl.mo_filter_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_filter_columns.params.FilterColumnsParamsExtractor;

public class FilterColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        FilterColumnsParamsExtractor extractor = new FilterColumnsParamsExtractor();
        List<ColumnModel> filterConditions = extractor.extractParams(params);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode inputData = data.deepCopy();
        ArrayNode resultData = objectMapper.createArrayNode();
        if (!inputData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (JsonNode item : inputData) {
            Boolean isValid = filterConditions.stream().allMatch(condition -> checkNode(condition, item));
            if (isValid) {
                resultData.add(item);
            }
        }
        return resultData;
    }

    private Boolean checkNode(ColumnModel condition, JsonNode data) {
        if (!data.has(condition.getColumn()) || data.get(condition.getColumn()).isNull()) return false;

        JsonNode valueNode = data.get(condition.getColumn());
        String compareValue = condition.getValue();
        String operator = condition.getOperator();

        if (valueNode.isTextual()) {
            return Utils.compareValue(valueNode.asText(), compareValue, operator);
        } else if (valueNode.isInt()) {
            return Utils.compareValue(valueNode.asInt(), Integer.parseInt(compareValue), operator);
        }
    
        return false;
    }
}
