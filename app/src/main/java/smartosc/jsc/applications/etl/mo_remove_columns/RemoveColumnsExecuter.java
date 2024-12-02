package smartosc.jsc.applications.etl.mo_remove_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_remove_columns.params.RemoveColumnsParamsExtractor;

public class RemoveColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        RemoveColumnsParamsExtractor extractor = new RemoveColumnsParamsExtractor();
        List<String> removeColumns = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (String column : removeColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data is invalid.");
                }
                if(!item.has(column)){
                    throw new RuntimeException("Column not isset : " + column);
                }
                ((ObjectNode) item).remove(column);
            }
        }

        return returnData;
    }

}
