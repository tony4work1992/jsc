package smartosc.jsc.applications.etl.mo_union;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_union.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_union.params.UnionParamsExtractor;

public class UnionExecuter implements Executable {
    private final Map<Integer, JsonNode> result;

    public UnionExecuter(Map<Integer, JsonNode> result) {
        this.result = result;
    }

    @Override
    public JsonNode execute(String params, JsonNode dataset) throws Exception {
        UnionParamsExtractor extractor = new UnionParamsExtractor();
        List<ColumnModel> nodes = extractor.extractParams(params);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode returnData = mapper.createArrayNode();
        ArrayNode data = (ArrayNode) dataset;
        for (ColumnModel node : nodes) {
            String nodeIds = node.getNodeIds();
            String[] ids = nodeIds.split(",");

            for (String id : ids) {
                Integer nodeId = Integer.parseInt(id);
                if (data.has(nodeId)) {
                    returnData.addAll((ArrayNode) result.get(nodeId));
                }
            }
        }

        return returnData;
    }
}