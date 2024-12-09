package smartosc.jsc.applications.etl.ba_nodes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.*;

public class NodeExecutionQueue {
    private final Queue<NodeCommand> commands = new LinkedList<>();

    public void addQueue(NodeCommand command) {
        commands.add(command);
    }

    public List<JsonNode> executeAll() throws Exception {
        List<JsonNode> nodeResults = new LinkedList<>();

        while (!commands.isEmpty()) {
            NodeCommand command = commands.poll();
            JsonNode dataset = getDataset(nodeResults, command);

            if (dataset.isEmpty()) {
                continue;
            }

            JsonNode nodeResult = command.node.execute(command.params, dataset);
            nodeResults.add(command.id, nodeResult);
        }

        return nodeResults;
    }

    private JsonNode getDataset(List<JsonNode> nodeResults, NodeCommand command) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode dataset = mapper.createArrayNode();

        if (command.parent.isEmpty()) {
            dataset.add(command.dataset);
        } else {
            command.parent.forEach(parentId -> {
                JsonNode parentResult = nodeResults.get(parentId.asInt());

                if (!parentResult.isEmpty()) {
                    dataset.add(parentResult);
                }
            });
        }

        return dataset;
    }
}