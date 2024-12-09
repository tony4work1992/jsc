package smartosc.jsc.applications.etl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import smartosc.jsc.applications.etl.ba_nodes.BaseExecute;
import smartosc.jsc.applications.etl.ba_nodes.ExecuteFactory;
import smartosc.jsc.applications.etl.ba_nodes.ExerciseInterface;

import java.util.HashMap;
import java.util.Map;

public class Exercise2 implements ExerciseInterface {
    @Override
    public Map<Integer, JsonNode> execute(String requestData, String jsonData) throws Exception {
        ExecuteFactory executeFactory = new ExecuteFactory();
        BaseExecute baseExecute = new BaseExecute(executeFactory);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode dataSet = objectMapper.readTree(requestData);
        JsonNode nodes = objectMapper.readTree(jsonData);
        JsonNode parentData = objectMapper.createObjectNode();
        Map<Integer, JsonNode> returnResult = new HashMap<>();

        if (!nodes.get("nodes").isEmpty()) {
            for (JsonNode node : nodes.get("nodes")) {
                Integer id = node.get("id").asInt();
                String nodeName = node.get("name").asText();
                JsonNode params = node.get("config");

                if (!node.get("parent").isEmpty()) {
                    ArrayNode parentNodeIds = (ArrayNode) node.get("parent");
                    Integer parentNodeId = parentNodeIds.get(0).asInt();
                    parentData = returnResult.get(parentNodeId);

                    System.out.println(parentData);
                }

                if (!nodeName.isEmpty()) {
                    String jsonString = objectMapper.writeValueAsString(params);
                    System.out.println(jsonString);
//                    continue;
                    JsonNode result = baseExecute.execute(nodeName, jsonString, parentData, returnResult);
//
//                    returnResult.put(id, result);
                }
            }
        }

        return returnResult;
    }
}