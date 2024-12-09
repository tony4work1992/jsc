package smartosc.jsc.applications.etl.mo_nodes.remove;

import com.fasterxml.jackson.databind.JsonNode;
import smartosc.jsc.applications.etl.ba_nodes.Node;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.mo_nodes.remove.params.RemoveParamExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class RemoveNode implements Node {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        RemoveParamExtractor extractor = new RemoveParamExtractor();
        JsonNode removeParams = extractor.extractParams(params);

        JsonNode dataset = data.deepCopy();
        List<String> updatedData = new ArrayList<>();

        if (!dataset.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (JsonNode item : dataset) {
            for (JsonNode row : item) {
                for (JsonNode removeParam : removeParams) {
                    List<String> removeColumns = Arrays.asList(removeParam.get("columns").asText().split(","));

                    for (String removeColumn : removeColumns) {
                        if (row.has(removeColumn)) {
                            ((ObjectNode) row).remove(removeColumn);
                        }
                    }
                }

                updatedData.add(row.toString());
            }
        }

        return mapper.readTree(updatedData.toString());
    }
}
