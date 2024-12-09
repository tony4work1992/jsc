package smartosc.jsc.applications.etl.mo_rename_columns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_rename_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_rename_columns.params.RenameColumnsParamsExtractor;

import java.util.List;

public class RenameColumnsExecute implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode dataSet) throws Exception {
        RenameColumnsParamsExtractor renameColumnsParamsExtractor = new RenameColumnsParamsExtractor();
        List<ColumnModel> renameColumns = renameColumnsParamsExtractor.extractParams(params);
        JsonNode returnData = dataSet.deepCopy();

        // Check Data
        if (!dataSet.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (ColumnModel columnName : renameColumns) {
            for (JsonNode item : returnData) {
                // check item data
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                // kiem tra xem co column can rename khong
                if (item.has(columnName.getColumn())) {
                    JsonNode value = item.get(columnName.getColumn());          // lay value cua column can rename
                    ((ObjectNode) item).remove(columnName.getColumn());         // xoa column can rename
                    ((ObjectNode) item).set(columnName.getNewColumn(), value);  // them column moi voi gia tri da lay o tren
                }
            }
        }
        return returnData;
    }
}
