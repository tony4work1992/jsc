package smartosc.jsc.applications.etl.mo_filter_columns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_filter_columns.params.FilterColumnsParamsExtractor;
import smartosc.jsc.applications.etl.mo_filter_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.ba_nodes.Utils;

import java.util.List;

public class FilterColumnsExecute implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode dataSet) throws Exception {
        FilterColumnsParamsExtractor filterColumnsParamsExtractor = new FilterColumnsParamsExtractor();
        List<ColumnModel> filterColumns = filterColumnsParamsExtractor.extractParams(params);

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode returnData = objectMapper.createArrayNode();

        // Check Data
        if (!dataSet.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (JsonNode item : dataSet) {
            boolean isValid = filterColumns.stream().allMatch(condition -> CheckData(condition, item));
            if (isValid) {
                returnData.add(item);
            }
        }

        return returnData;
    }

    private boolean CheckData(ColumnModel column, JsonNode item) {
        // check valueCurrent
        if ((item.get(column.getColumn()).isNull())) {
            return false;
        }

        // get valueCurrent
        JsonNode valueCurrent = item.get(column.getColumn());
        String operator = column.getOperator();     //get dk compare
        String compareValue = column.getValue();    // get gia tri compare

        if (valueCurrent.isTextual()) {     //compare Text
            return Utils.compareValue(valueCurrent.asText(), compareValue, operator);
        } else if (valueCurrent.isInt()) {  //compare Int
            return Utils.compareValue(valueCurrent.asInt(), Integer.parseInt(compareValue), operator);
        }

        return false;
    }
}
