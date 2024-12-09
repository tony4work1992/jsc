package smartosc.jsc.applications.etl.mo_union_nodes;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_union_nodes.params.UnionNodesParamsExtractor;
import smartosc.jsc.applications.etl.mo_union_nodes.params.ColumnModel;

public class UnionNodesExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        UnionNodesParamsExtractor extractor = new UnionNodesParamsExtractor();
        List<ColumnModel> unionNodes = extractor.extractParams(params);
        // return unionDatasets(unionNodes);
        return data;
    }

    private JsonNode unionDatasets(List<JsonNode> datasets) {
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> unionResult = new ArrayList<>();
        for (JsonNode dataset : datasets) {
            for (JsonNode node : dataset) {
                unionResult.add(node);
            }
        }
        return mapper.valueToTree(unionResult);
    }
}
