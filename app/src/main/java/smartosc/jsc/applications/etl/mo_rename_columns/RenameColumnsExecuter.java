package smartosc.jsc.applications.etl.mo_rename_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_rename_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_rename_columns.params.RenameColumnsParamExtractor;

public class RenameColumnsExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        RenameColumnsParamExtractor extractor = new RenameColumnsParamExtractor();
        List<ColumnModel> renameColumns = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        for (ColumnModel column : renameColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                String columnName = column.getColumn();
                String newColumnName = column.getNewColumnName();

                if (!item.has(columnName)) {
                    throw new RuntimeException("Rename Column: Column " + columnName + " does not exists");
                }
                ((ObjectNode) item).put(newColumnName, item.get(columnName).asText());
                ((ObjectNode) item).remove(columnName);
            }
        }
        return returnData;
    }

}
