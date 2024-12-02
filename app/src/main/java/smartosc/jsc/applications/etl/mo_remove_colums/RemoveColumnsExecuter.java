package smartosc.jsc.applications.etl.mo_remove_colums;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;

import java.util.List;

public class RemoveColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode removeColumns = mapper.readTree(params);
        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        for (JsonNode column : removeColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }
                String columnName = column.textValue();
                if (!item.has(columnName)) {
                    throw new RuntimeException("This column " + columnName + " doesn't exist.");
                }
                ((ObjectNode) item).remove(columnName);
            }
        }
        return returnData;

    }

}
