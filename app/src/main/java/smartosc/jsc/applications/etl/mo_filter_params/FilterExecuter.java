package smartosc.jsc.applications.etl.mo_filter_params;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_filter_params.constants.OperatorConstant;
import smartosc.jsc.applications.etl.mo_filter_params.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_filter_params.params.FilterParamsExtractor;

import java.util.List;

public class FilterExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode dataset) throws Exception {
        try {
            FilterParamsExtractor extractor = new FilterParamsExtractor();
            List<ColumnModel> filterConditions = extractor.extractParams(params);

            JsonNode returnDataClone = dataset.deepCopy();
            JsonNode returnData = dataset.deepCopy();


            if (!returnDataClone.isArray()) {
                throw new RuntimeException("Data is not an array");
            }

            for (ColumnModel filterCondition : filterConditions) {
                for (int i = 0; i < returnDataClone.size(); i++) {
                    Boolean isValid = checkCondition(returnDataClone.get(i), filterCondition);
                    if (!isValid) {
                        ((ArrayNode) returnData).remove(i);
                    }
                }

            }
            return returnData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public Boolean checkCondition(JsonNode data, ColumnModel filterCondition) {
        String column = filterCondition.column;

        if (data.has(column)) {
            JsonNode value = data.get(column);
            String currentValue = value.asText();

            String filterValue = (String) filterCondition.value;

            return switch (filterCondition.operator) {
                case OperatorConstant.EQUAL -> currentValue.equals(filterValue);
                case OperatorConstant.GREATER_THAN -> currentValue.compareTo(filterValue) > 0;
                case OperatorConstant.LESS_THAN -> currentValue.compareTo(filterValue) < 0;
                default -> false;
            };

        } else {
            throw new RuntimeException("Column " + column + " is not a valid column");
        }
    }

}
