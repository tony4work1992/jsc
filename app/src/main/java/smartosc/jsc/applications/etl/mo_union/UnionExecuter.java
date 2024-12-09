package smartosc.jsc.applications.etl.mo_union;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import smartosc.jsc.applications.etl.ba_nodes.Executable;

public class UnionExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode newData = objectMapper.createArrayNode();
        String[] parentIds = objectMapper.readValue(params, String[].class);
        for (String parentId : parentIds) {
            if (data.has(parentId)) {
                for (JsonNode node : data.get(parentId)) {
                    newData.add(node);
                }
            }
        }
        return newData;
    }

}
