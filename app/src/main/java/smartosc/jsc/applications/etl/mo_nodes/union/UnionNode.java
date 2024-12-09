package smartosc.jsc.applications.etl.mo_nodes.union;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.ba_nodes.Node;

public class UnionNode implements Node {

    @Override
    public JsonNode execute(String params, JsonNode dataset) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode updatedData = mapper.createArrayNode();

        for (JsonNode item : dataset) {
            for (JsonNode row : item) {
                updatedData.add(row);
            }
        }

        return updatedData;
    }
}
