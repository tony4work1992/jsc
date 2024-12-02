package smartosc.jsc.applications.etl.mo_remove_columns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_remove_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_remove_columns.params.RemoveColumnsParamsExtractor;

import java.util.List;

public class RemoveColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode dataset) throws Exception {
        RemoveColumnsParamsExtractor extractor = new RemoveColumnsParamsExtractor();
        List<ColumnModel> removeColumns = extractor.extractParams(params);

        JsonNode returnData = dataset.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (ColumnModel removeColumn : removeColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }
                String column = removeColumn.getColumn();

                if(item.has(column)) {
                    ((ObjectNode)item).remove(column);
                } else {
                    throw new RuntimeException(column + " is not found");
                }
            }
        }
        return returnData;

    }


}

