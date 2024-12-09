package smartosc.jsc.applications.etl.mo_filter_columns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_filter_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_filter_columns.params.FilterColumnsParamsExtractor;

import java.util.List;

public class FilterColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        FilterColumnsParamsExtractor extractor = new FilterColumnsParamsExtractor();
        List<ColumnModel> renameColumns = extractor.extractParams(params);

        JsonNode baseData = data.deepCopy();
        ArrayNode returnData = new ArrayNode(null);
        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        for (ColumnModel column : renameColumns) {
            for (JsonNode item : baseData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }
                String columnName = column.getColumn();
                String condition = column.getCondition();
                String filterValue = column.getValue();
                if (!item.has(columnName)) {
                    throw new RuntimeException("This column " + columnName + " doesn't exist.");
                }
                String columnValue = item.get(columnName)==null?null:item.get(columnName).textValue();
                if(this.filterData(columnValue,condition,filterValue)){
                    returnData.add(item);
                }
            }
        }
        return returnData;

    }

    private boolean filterData(String columnValue, String condition, String compareValue){
        switch(condition) {
            case "contains":
                columnValue = columnValue.toLowerCase();
                compareValue = compareValue.toLowerCase();
                return columnValue.contains(compareValue);
            case "eq":
                //
                return false;
            case "gt":
                return false;
            case "lt":
                return false;
            case "gteq":
                return false;
            case "lteq":
                return false;
            default:
                return false;
        }
    }

}
