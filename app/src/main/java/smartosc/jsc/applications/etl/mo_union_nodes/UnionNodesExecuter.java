package smartosc.jsc.applications.etl.mo_union_nodes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_union_nodes.params.NodeModel;
import smartosc.jsc.applications.etl.mo_union_nodes.params.UnionNodesParamsExtractor;

import java.util.List;

public class UnionNodesExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        UnionNodesParamsExtractor extractor = new UnionNodesParamsExtractor();
        List<NodeModel> nodes = extractor.extractParams(params);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode returnData = mapper.createArrayNode();
//        ArrayNode data = (ArrayNode) dataset;
        for (NodeModel nodeModel : nodes) {
            String parentIds = nodeModel.getIds();
            String[] ids = parentIds.split(",");
            for (String id : ids) {
                if (data.has(id)) {
                    for (JsonNode node : data.get(id)) {
                        returnData.add(node);
                    }
                }
            }
        }
        return returnData;
    }
}
