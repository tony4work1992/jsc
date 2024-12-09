package smartosc.jsc.applications.etl.mo_filter_values;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.constants.OperatorConstants;
import smartosc.jsc.applications.etl.mo_filter_values.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_filter_values.params.FilterValuesParamsExtractor;

public class FilterValuesExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode dataset) throws Exception {
        FilterValuesParamsExtractor extractor = new FilterValuesParamsExtractor();
        List<ColumnModel> filterConditions = extractor.extractParams(params);

        JsonNode returnData = dataset.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        // Check dieu kien neu co 1 filter khong thoa man thi xoa khoi returnData
        for (ColumnModel filterCondition : filterConditions) {
            for (int i = 0; i < returnData.size(); i++) {
                Boolean isValid = checkCondition(returnData.get(i), filterCondition);
                if (!isValid) {
                    ((ArrayNode) returnData).remove(i);
                }
            }

        }
        return returnData;
    }


    public Boolean checkCondition(JsonNode data, ColumnModel filterCondition) {
        // Get ten column can filter
        String column = filterCondition.getColumn();
        
        if (data.has(column)) {
            // Get current value
            JsonNode value = data.get(column);
            String currentValue = value.asText();

            // Get filter value
            String filterValue = (String) filterCondition.getValue();

            switch (filterCondition.getOperator()) {
                case OperatorConstants.EQUAL_OPERATOR:
                    return currentValue.equals(filterValue);
                case OperatorConstants.GREATER_OPERATOR:
                    return currentValue.compareTo(filterValue) > 0;
                case OperatorConstants.LESS_OPERATOR:
                    return currentValue.compareTo(filterValue) < 0;
                case OperatorConstants.LIKE_OPERATOR:
                    return currentValue.contains(filterValue);
            }

        }
        return false;
    }

}