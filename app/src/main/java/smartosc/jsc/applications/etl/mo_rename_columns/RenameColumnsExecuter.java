package smartosc.jsc.applications.etl.mo_rename_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_rename_columns.params.RenameColumnsParamsExtractor;
import smartosc.jsc.applications.etl.mo_rename_columns.params.ColumnModel;

public class RenameColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        RenameColumnsParamsExtractor extractor = new RenameColumnsParamsExtractor();
        List<ColumnModel> renameColumns = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (ColumnModel column : renameColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data is invalid.");
                }
                if(!item.has(column.getOldColumnName())){
                    throw new RuntimeException("Column not isset : " + column.getOldColumnName());
                }
                // Set value for New Column
                ((ObjectNode) item).set(column.getNewColumnName(), item.get(column.getOldColumnName()));
                //Remove old column
                ((ObjectNode) item).remove(column.getOldColumnName());
            }
        }

        return returnData;
    }

}
