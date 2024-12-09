package smartosc.jsc.applications.etl.transform.mo_union;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.transform.ba_nodes.Executable;
import smartosc.jsc.applications.etl.transform.mo_union.params.UnionParamsExtractor;

public class UnionExecutor implements Executable {

    private final Map<String, JsonNode> listResults;

    public UnionExecutor(Map<String, JsonNode> listResults) {
        this.listResults = listResults;
    }

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UnionParamsExtractor extractor = new UnionParamsExtractor();
        List<String> parentIds = extractor.extractParams(params);
        ArrayNode unionResult = objectMapper.createArrayNode();

        for (String parentId : parentIds) {
            unionResult.addAll((ArrayNode) listResults.get(parentId));
        }
        return unionResult;
    }

}
