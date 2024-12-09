package smartosc.jsc.applications.etl.mo_rename_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_rename_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_rename_columns.params.RenameColumnsParamsExtractor;

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
                
                // Lay ten column can rename
                String oldColumn = renameColumn.getOldColumn();
                String newColumn = renameColumn.getNewColumn();

                // Chi rename neu column ton tai
                if(item.has(oldColumn)) {
                    JsonNode value = item.get(oldColumn);
                    ((ObjectNode)item).remove(oldColumn);
                    ((ObjectNode)item).set(newColumn, value);
                }
            }
        }
        return returnData;

    }

}
