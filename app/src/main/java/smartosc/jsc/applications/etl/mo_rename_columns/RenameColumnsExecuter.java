package smartosc.jsc.applications.etl.mo_rename_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_rename_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_rename_columns.params.RenameColumnsParamsExtractor;

public class RenameColumnsExecuter implements Executable{
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        RenameColumnsParamsExtractor extractor = new RenameColumnsParamsExtractor();
        List<ColumnModel> renameColumns = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (ColumnModel renameColumn : renameColumns) {
            for (JsonNode item : returnData) {
                if (item.isObject()) {
                    ObjectNode objectNode = (ObjectNode) item;
                    JsonNode newColumn = objectNode.get(renameColumn.getOldColumn());

                    if (newColumn != null) {
                        objectNode.set(renameColumn.getNewColumn(), newColumn);
                        objectNode.remove(renameColumn.getOldColumn());
                    }
                }
            }
        }

        return returnData;
    }
}
