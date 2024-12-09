package smartosc.jsc.applications.etl.mo_transformer.executors.mo_remove_column;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_remove_column.params.RemoveColumnParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_rename_column.params.RenameColumnParamsModel;

import java.util.List;
import java.util.Map;

public class RemoveColumnExecutor implements Executable<List<RemoveColumnParamsModel>> {
    @Override
    public Map<Number, JsonNode> execute(List<RemoveColumnParamsModel> params, Map<Number, JsonNode> datasetByNodes, int id, int parentId) throws Exception {
        System.out.println("Executing RemoveColumnExecutor");
        try {
            JsonNode data;
            if (parentId != -1) {
                data = datasetByNodes.get(parentId);
            } else {
                data = datasetByNodes.get(0);
            }

            if (data == null || data.isNull()) {
                throw new Exception("Dataset is empty");
            }

            if (!data.isArray()) {
                throw new Exception("Dataset is not an array");
            }

            ObjectMapper mapper = new ObjectMapper();
            ArrayNode returnData = mapper.createArrayNode();
            data.forEach(item -> {
                ObjectNode objectNode = (ObjectNode) item;
                for (RemoveColumnParamsModel removeColumnModel : params) {
                    String columnName = removeColumnModel.getColumn();
                    if (objectNode.has(columnName)) {
                        objectNode.remove(columnName);
                    }
                }
                returnData.add(objectNode);
            });

            datasetByNodes.put(id, returnData.deepCopy());
        } catch (Exception e) {
            System.out.println("RemoveColumnExecutor ERR: " + e.getMessage());
        }
        return datasetByNodes;
    }
}
