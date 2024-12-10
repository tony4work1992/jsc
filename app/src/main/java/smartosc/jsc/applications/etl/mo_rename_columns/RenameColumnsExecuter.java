package smartosc.jsc.applications.etl.mo_rename_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_rename_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_rename_columns.params.RenameColumnsParamsExtractor;

public class RenameColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        RenameColumnsParamsExtractor extractor = new RenameColumnsParamsExtractor();
        List<ColumnModel> renameColumns = extractor.extractParams(params);

        ArrayNode returnData = (ArrayNode) data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        for (JsonNode item : returnData) {
            if (!item.isObject()) {
                throw new RuntimeException("Data item is not an object");
            }

            ObjectNode objectItem = (ObjectNode) item;
            for (ColumnModel columnModel : renameColumns) {
                String currentColumnName = columnModel.getCurrentColumnName();
                String newColumnName = columnModel.getNewColumnName();
                if (objectItem.has(currentColumnName)) {
                    JsonNode value = objectItem.get(currentColumnName);
                    objectItem.set(newColumnName, value);
                    objectItem.remove(currentColumnName);
                }
            }
        }
        return returnData;

    }

}
