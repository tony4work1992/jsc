package smartosc.jsc.applications.etl.mo_remove_columns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_remove_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_remove_columns.params.RemoveColumnsParamExtractor;

public class RemoveColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        RemoveColumnsParamExtractor extractor = new RemoveColumnsParamExtractor();
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

                String columnName = column.getColumn();

                if(!item.has(columnName)) {
                    throw new RuntimeException("Remove Column: Column " + columnName + " does not exists");
                }
                ((ObjectNode) item).remove(columnName);
            }
        }
        return returnData;
    }

}
