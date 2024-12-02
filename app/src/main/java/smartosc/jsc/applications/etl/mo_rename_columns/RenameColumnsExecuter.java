package smartosc.jsc.applications.etl.mo_rename_columns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_rename_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_rename_columns.params.RenameColumnsParamsExtractor;

import java.util.List;

public class RenameColumnsExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode dataset) throws Exception {
        RenameColumnsParamsExtractor extractor = new RenameColumnsParamsExtractor();
        List<ColumnModel> renameColumns = extractor.extractParams(params);

        JsonNode returnData = dataset.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (ColumnModel renameColumn : renameColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                String currentColumn = renameColumn.getCurrentColumn();
                String newColumn = renameColumn.getNewColumn();

                // Check currentName is existed
                if (item.has(currentColumn)) {
                    JsonNode value = item.get(currentColumn);
                    ((ObjectNode) item).remove(currentColumn);
                    ((ObjectNode) item).set(newColumn, value);
                }
                else {
                    throw new RuntimeException("Current name not found");
                }
            }
        }
        return returnData;

    }
}
