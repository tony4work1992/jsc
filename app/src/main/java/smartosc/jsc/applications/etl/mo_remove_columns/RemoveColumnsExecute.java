package smartosc.jsc.applications.etl.mo_remove_columns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_remove_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_remove_columns.params.RemoveColumnsParamsExtractor;

import java.util.List;

public class RemoveColumnsExecute implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode dataSet) throws Exception {
        RemoveColumnsParamsExtractor removeColumnsParamsExtractor = new RemoveColumnsParamsExtractor();
        List<ColumnModel> removeColumns = removeColumnsParamsExtractor.extractParams(params);
        JsonNode returnData = dataSet.deepCopy();

        // Check Data
        if (!dataSet.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (ColumnModel columnName : removeColumns) {
            for (JsonNode item : returnData) {
                // check item data
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                // kiem tra xem co column can remove khong
                if (item.has(columnName.getColumn())) {
                    ((ObjectNode) item).remove(columnName.getColumn());         // xoa column can remove
                }
            }
        }
        return returnData;
    }
}
