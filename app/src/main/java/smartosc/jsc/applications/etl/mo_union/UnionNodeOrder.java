package smartosc.jsc.applications.etl.mo_union;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnionNodeOrder {
    public static List<String> orderNode(String params, String jsonConfigData) {
        Set<Integer> filterIds = new HashSet<>();
        for (String id : params.split(",")) {
            filterIds.add(Integer.parseInt(id));
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(jsonConfigData);
        } catch (JsonProcessingException ex) {
        }
        JsonNode nodes = rootNode.get("nodes");

        Map<Integer, JsonNode> nodeMap = new HashMap<>();
        nodes.fields().forEachRemaining(entry -> {
            int id = Integer.parseInt(entry.getKey());
            nodeMap.put(id, entry.getValue());
        });

        List<String> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            int currentId = queue.poll();
            if (filterIds.contains(currentId)) {
                JsonNode currentNode = nodeMap.get(currentId);
                result.add(currentNode.get("name").asText());
                JsonNode children = currentNode.get("children");
                if (children.isArray()) {
                    children.forEach(child -> queue.add(child.asInt()));
                }
            }
        }
        
        return result;
    }
}
