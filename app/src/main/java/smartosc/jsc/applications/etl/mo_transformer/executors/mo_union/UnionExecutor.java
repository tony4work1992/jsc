package smartosc.jsc.applications.etl.mo_transformer.executors.mo_union;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_remove_column.params.RemoveColumnParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_rename_column.params.RenameColumnParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_union.params.UnionParamsModel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UnionExecutor implements Executable<List<UnionParamsModel>> {
    @Override
    public Map<Number, JsonNode> execute(List<UnionParamsModel> params, Map<Number, JsonNode> datasetByNodes, int id, int parentId) throws Exception {
        System.out.println("Executing UnionExecutor");
        try {
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode returnData = mapper.createArrayNode();
            for (UnionParamsModel unionParamsModel : params) {
                int[] nodeIds = unionParamsModel.getNodeIds();
                Arrays.stream(nodeIds).forEach(nodeId -> {
                    ArrayNode mergedNode = datasetByNodes.get(nodeId).deepCopy();
                    mergedNode.elements().forEachRemaining(returnData::add);
                });
            }

            datasetByNodes.put(id, returnData);
        } catch (Exception e) {
            System.out.println("UnionExecutor ERR: " + e.getMessage());
        }
        return datasetByNodes;
    }
}
