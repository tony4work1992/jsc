package smartosc.jsc.applications;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import smartosc.jsc.applications.etl.ba_factory.ExecuterFactory;
import smartosc.jsc.applications.etl.ba_nodes.Executable;

public class App {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode config = mapper.readTree(new File("app/src/main/java/smartosc/jsc/applications/config.json"));

            Map<Integer, JsonNode> nodeResults = new HashMap<>();

            JsonNode nodes = config.get("nodes");
            for (JsonNode node : nodes) {
                int id = node.get("id").asInt();
                String name = node.get("name").asText();
                String params = node.get("config").asText();

                // Get the parent node
                JsonNode parentData = null;
                if (node.has("parent") && !node.get("parent").isNull()) {
                    ArrayNode parentIds = (ArrayNode) node.get("parent");
                    parentData = mapper.createArrayNode();
                    for (JsonNode parentId : parentIds) {
                        ((ArrayNode) parentData).addAll((ArrayNode) nodeResults.get(parentId.asInt()));
                    }
                }

                // Execute with parent data node
                Executable module = ExecuterFactory.getExecutable(name, nodeResults);
                JsonNode result = module.execute(params, parentData);
                nodeResults.put(id, result);
            }
            String unionString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(nodeResults.get(8));
            System.out.println("-----------Data After Run Home Work 2--------------");
            System.out.println(unionString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
