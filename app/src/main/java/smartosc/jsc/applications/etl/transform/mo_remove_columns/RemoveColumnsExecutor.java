package smartosc.jsc.applications.etl.transform.mo_remove_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.transform.ba_nodes.Executable;
import smartosc.jsc.applications.etl.transform.mo_remove_columns.params.RemoveColumnsModel;
import smartosc.jsc.applications.etl.transform.mo_remove_columns.params.RemoveColumnsParamsExtrator;

public class RemoveColumnsExecutor implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {

        RemoveColumnsParamsExtrator extractor = new RemoveColumnsParamsExtrator();
        List<RemoveColumnsModel> removeColumnModels = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();
        if (!returnData.isArray()) {
            throw new RuntimeException("[RemoveColumnsExecutor] Data is not an array");
        }
        for (RemoveColumnsModel column : removeColumnModels) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("[RemoveColumnsExecutor] Data item is not an object");
                }
                if (!item.has(column.getColumn())) {
                    throw new RuntimeException("[RemoveColumnsExecutor] Column: " + column.getColumn() + " is not exist");
                }
                ((ObjectNode) item).remove(column.getColumn());
            }
        }
        return returnData;
    }
}
