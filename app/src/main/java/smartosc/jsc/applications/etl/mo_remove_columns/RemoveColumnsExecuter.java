package smartosc.jsc.applications.etl.mo_remove_columns;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;

public class RemoveColumnsExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        // Ensure that the root node is an ObjectNode (which allows for modification)
        for (JsonNode item : returnData) {
            if (item.isObject()) {
                ObjectNode objectNode = (ObjectNode) item;
    
                // Remove the "age" column/key from the ObjectNode
                objectNode.remove(params);
            }
        }

        return returnData;
    }
}
