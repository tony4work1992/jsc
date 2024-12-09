package smartosc.jsc.applications.etl.mo_union_columns;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_union_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_union_columns.params.UnionColumnsParamsExtractor;

public class UnionColumnsExecute implements Executable {

    private final Map<Integer, JsonNode> result;

    public UnionColumnsExecute(Map<Integer, JsonNode> result) {
        this.result = result;
    }

    @Override
    public JsonNode execute(String params, JsonNode dataset) throws Exception {
        UnionColumnsParamsExtractor extractor = new UnionColumnsParamsExtractor();
        List<ColumnModel> nodes = extractor.extractParams(params);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode returnData = mapper.createArrayNode();

        for (ColumnModel node : nodes) {
            String nodeIds = node.getNodeIds();
            String[] ids = nodeIds.split(",");

            JsonNode jsonNodes0 = result.get(Integer.parseInt(ids[0]));
            JsonNode jsonNodes1 = result.get(Integer.parseInt(ids[1]));
            if (!jsonNodes0.isEmpty() && !jsonNodes1.isEmpty()) {
                for (JsonNode jsonNode0 : jsonNodes0) {
                    JsonNode returnAdd = jsonNode0;
                    for (JsonNode jsonNode1 : jsonNodes1) {
                        int idNode0 = Integer.parseInt(jsonNode0.get("id").asText());
                        int idNode1 = Integer.parseInt(jsonNode1.get("id").asText());

                        if (idNode0 == idNode1) {
                            returnAdd = jsonNode1;
                            break;
                        }
                    }
                    returnData.add(returnAdd);
                }
            }
        }

        return returnData;
    }
}