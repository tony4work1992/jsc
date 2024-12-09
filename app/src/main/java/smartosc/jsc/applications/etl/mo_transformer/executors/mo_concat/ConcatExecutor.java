package smartosc.jsc.applications.etl.mo_transformer.executors.mo_concat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_concat.params.ConcatParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_remove_column.params.RemoveColumnParamsModel;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class ConcatExecutor implements Executable<List<ConcatParamsModel>> {
    @Override
    public Map<Number, JsonNode> execute(List<ConcatParamsModel> params, Map<Number, JsonNode> datasetByNodes, int id, int parentId) throws Exception {
        System.out.println("Executing ConcatExecutor");
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
                for (ConcatParamsModel concatModel : params) {
                    StringJoiner concatValues = new StringJoiner(concatModel.getDelimiter());
                    String columnName = concatModel.getNewColumnName();
                    for (String concatColumn : concatModel.getConcatColumns()) {
                        if (objectNode.has(concatColumn)) {
                            concatValues.add(objectNode.get(concatColumn).asText());
                        }
                    }
                    objectNode.put(columnName, concatValues.toString());
                }
                returnData.add(objectNode);
            });

            datasetByNodes.put(id, returnData.deepCopy());
        } catch (Exception e) {
            System.out.println("ConcatExecutor ERR: " + e.getMessage());
        }
        return datasetByNodes;
    }
}
