package smartosc.jsc.applications.etl.mo_rename_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_rename_columns.params.RenameColumnModel;
import smartosc.jsc.applications.etl.mo_rename_columns.params.RenameColumnsParamsExtractor;

public class RenameColumnsExecutor implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {

        RenameColumnsParamsExtractor extractor = new RenameColumnsParamsExtractor();
        List<RenameColumnModel> renameColumnModels = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();
        if (!returnData.isArray()) {
            throw new RuntimeException("[RenameColumnsExecutor] Data is not an array");
        }
        for (RenameColumnModel column : renameColumnModels) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("[RenameColumnsExecutor] Data item is not an object");
                }
                if (column.getOldColumn() == null || column.getOldColumn().isEmpty() || !item.has(column.getOldColumn())) {
                    throw new RuntimeException("[RenameColumnsExecutor] Data of column " + column.getOldColumn() + " is required");
                }
                if (column.getNewColumn() == null || column.getNewColumn().isEmpty()) {
                    throw new RuntimeException("[RenameColumnsExecutor] New column name is required");
                }
                ((ObjectNode) item).set(column.getNewColumn(), item.get(column.getOldColumn()));
                ((ObjectNode) item).remove(column.getOldColumn());
            }
        }
        return returnData;
    }
}
