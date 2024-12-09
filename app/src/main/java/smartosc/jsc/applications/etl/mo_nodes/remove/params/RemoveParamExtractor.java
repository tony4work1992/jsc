package smartosc.jsc.applications.etl.mo_nodes.remove.params;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;

public class RemoveParamExtractor implements NodeParamsExtractor<JsonNode> {

    public JsonNode extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonParams = mapper.readTree(params);

        if (params == null || params.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }

        for (JsonNode jsonParam : jsonParams) {
            if (!jsonParam.has("columns")) {
                throw new RuntimeException("Columns are required");
            }
        }

        return jsonParams;
    }
}
