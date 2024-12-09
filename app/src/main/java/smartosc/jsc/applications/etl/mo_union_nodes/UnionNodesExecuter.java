package smartosc.jsc.applications.etl.mo_union_nodes;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.JsonNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_union_nodes.params.UnionNodesParamsExtractor;

public class UnionNodesExecuter implements Executable {

    private final Map<Integer, JsonNode> nodeResults;

    public UnionNodesExecuter(Map<Integer, JsonNode> nodeResults) {
        this.nodeResults = nodeResults;
    }

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UnionNodesParamsExtractor extractor = new UnionNodesParamsExtractor();
        ArrayNode nodeIds = extractor.extractParams(params);
        ArrayNode unionResult = objectMapper.createArrayNode();

        for (JsonNode nodeId : nodeIds) {
            unionResult.addAll((ArrayNode) nodeResults.get(nodeId.asInt()));
        }
        return unionResult;
    }
}
