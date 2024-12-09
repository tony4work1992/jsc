package smartosc.jsc.applications.etl.mo_transformer.executors.mo_add_column;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_add_column.params.AddColumnParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_concat.params.ConcatParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_rename_column.params.RenameColumnParamsModel;

import java.util.List;
import java.util.Map;

public class AddColumnsExecutor implements Executable<List<AddColumnParamsModel>> {
    @Override
    public Map<Number, JsonNode> execute(List<AddColumnParamsModel> params, Map<Number, JsonNode> datasetByNodes, int id, int parentId) throws Exception {
        System.out.println("Executing AddColumnsExecutor");
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
                for (AddColumnParamsModel addColumnModel : params) {
                    String columnName = addColumnModel.getColumn();
                    String value = addColumnModel.getValue();
                    objectNode.put(columnName, value);
                }
                returnData.add(objectNode);
            });

            datasetByNodes.put(id, returnData.deepCopy());
        } catch (Exception e) {
            System.out.println("AddColumnsExecutor ERR: " + e.getMessage());
        }
        return datasetByNodes;
    }
}
