package smartosc.jsc.applications.etl.mo_filter_values;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_filter_values.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_filter_values.utils.FilterUtils;
import smartosc.jsc.applications.etl.mo_filter_values.params.FilterValuesParamsExtractor;

public class FiltterValuesExecuter implements Executable {
    public JsonNode execute(String params, JsonNode dataset) throws Exception {
        FilterValuesParamsExtractor filterColumnsParamExtractor = new FilterValuesParamsExtractor();
        List<ColumnModel> filterConditions = filterColumnsParamExtractor.extractParams(params);
        // Validate input data
        if (!dataset.isArray()) {
            throw new RuntimeException("Data set is invalid");
        }
        ArrayNode filteredArrayNode = new ArrayNode(null);
        for (JsonNode item : dataset) {
            Boolean isApplyCondition = true;
            for (ColumnModel condition : filterConditions) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }
                if(!item.has(condition.getColumnName())){
                    throw new RuntimeException("Column not isset : " + condition.getColumnName());
                }
                String columnValue = item.get(condition.getColumnName()).toString();
                columnValue = columnValue.replace("\"", "");
                if (!FilterUtils.applyComparison(columnValue, condition.getCondition(),
                        condition.getValue())) {
                            isApplyCondition = false;
                            break;
                }
            }
            if(isApplyCondition) filteredArrayNode.add(item);
        }

        return filteredArrayNode;
    }

}
