package smartosc.jsc.applications.etl.mo_union_datasets;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;

public class UnionDatasetsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode unionDatasets = mapper.readTree(params);
        ArrayNode result = mapper.createArrayNode();
        for (JsonNode datasetId : unionDatasets) {
            if (result.isEmpty()) {
                for (JsonNode item : data.get(datasetId.asText())) {
                    result.add(item);
                }
            } else {
                for (JsonNode item : data.get(datasetId.asText())) {
                    boolean isReplaced = false;
                    for (int i = 0; i < result.size(); i++) {
                        JsonNode existingItem = result.get(i);
                        if (existingItem.get("sku").asText().equals(item.get("sku").asText())) {
                            result.set(i, item);
                            isReplaced = true;
                            break;
                        }
                    }
                    if (!isReplaced) {
                        result.add(item);
                    }
                }
            }
        }

        return result;
    }
}
