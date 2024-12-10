package smartosc.jsc.applications.etl.mo_remove_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_remove_columns.params.RemoveColumnsParamsExtractor;

public class RemoveColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        RemoveColumnsParamsExtractor extractor = new RemoveColumnsParamsExtractor();
        List<String> columnsToRemove = extractor.extractParams(params);

        ArrayNode returnData = (ArrayNode) data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        for (JsonNode item : returnData) {
            if (!item.isObject()) {
                throw new RuntimeException("Data item is not an object");
            }

            ObjectNode objectItem = (ObjectNode) item;
            for (String columnName : columnsToRemove) {
                if (objectItem.has(columnName)) {
                    objectItem.remove(columnName);
                }
            }
        }
        return returnData;
    }
}
