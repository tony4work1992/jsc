package smartosc.jsc.applications.etl.mo_remove_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_remove_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_remove_columns.params.RemoveColumnsParamsExtractor;

public class RemoveColumnsExecuter implements Executable {
    public JsonNode execute(String params, JsonNode data) throws Exception {
        RemoveColumnsParamsExtractor extractor = new RemoveColumnsParamsExtractor();
        List<ColumnModel> removeColumns = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();
        
        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        for (ColumnModel column : removeColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                String deleteColumnName = column.getColumn();
                if (!item.has(deleteColumnName)) {
                    throw new RuntimeException("Column does not exist");
                }
                
                ((ObjectNode) item).remove(deleteColumnName);
            }
        }
        return returnData;
    }
}
