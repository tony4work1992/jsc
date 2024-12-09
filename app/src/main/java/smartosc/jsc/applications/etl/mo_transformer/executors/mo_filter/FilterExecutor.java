package smartosc.jsc.applications.etl.mo_transformer.executors.mo_filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_concat.params.ConcatParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_filter.params.FilterParamsModel;

import java.util.List;
import java.util.Map;

public class FilterExecutor implements Executable<List<FilterParamsModel>> {
    @Override
    public Map<Number, JsonNode> execute(List<FilterParamsModel> params, Map<Number, JsonNode> datasetByNodes, int id, int parentId) throws Exception {
        System.out.println("Executing FilterExecutor");
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
                boolean isValid = true;
                for (FilterParamsModel filterModel : params) {
                    String column = filterModel.getColumn();
                    String value = filterModel.getValue();

                    switch (filterModel.getOperator()) {
                        case GTEQ:
                            isValid = item.path(column).asDouble() >= Double.parseDouble(value);
                            break;
                        case GT:
                            isValid = item.path(column).asDouble() > Double.parseDouble(value);
                            break;
                        case LTEQ:
                            isValid = item.path(column).asDouble() <= Double.parseDouble(value);
                            break;
                        case LT:
                            isValid = item.path(column).asDouble() < Double.parseDouble(value);
                            break;
                        case IN:
                            isValid = item.path(column).asText("").contains(value);
                            break;
                        case EQ:
                        default:
                            isValid = item.path(column).asText("").equals(value);
                            break;
                    }
                }
                if (isValid) {
                    returnData.add(objectNode);
                }
            });

            datasetByNodes.put(id, returnData.deepCopy());
        } catch (Exception e) {
            System.out.println("FilterExecutor ERR: " + e.getMessage());
            return null;
        }
        return datasetByNodes;
    }
}
