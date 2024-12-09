package smartosc.jsc.applications.etl.modules.mo_tree_builder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.modules.base.AbstractEtlFactory;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;
import smartosc.jsc.applications.etl.modules.mo_factory.EtlFactoryPool;
import smartosc.jsc.applications.etl.test.TestInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GraphBuilder {

    public static final Integer INSTRUCTION_ROOT_ID = 1;

    public static InstructionGraph buildGraph() {
//        String json = "{ \"nodes\": { \"0\": { \"id\": 0, \"name\": \"LoadData\", \"config\": {}, \"children\": [1], \"parent\": null }, \"1\": { \"id\": 1, \"name\": \"RenameColumns\", \"config\": {}, \"children\": [2], \"parent\": null }, \"2\": { \"id\": 2, \"name\": \"Concat\", \"config\": {}, \"children\": [3], \"parent\": [1] }, \"3\": { \"id\": 3, \"name\": \"Remove\", \"config\": {}, \"children\": [4, 5], \"parent\": [2] }, \"4\": { \"id\": 4, \"name\": \"Filter\", \"config\": {}, \"children\": [], \"parent\": [3] }, \"5\": { \"id\": 5, \"name\": \"Filter\", \"config\": {}, \"children\": [], \"parent\": [3] }, \"6\": { \"id\": 6, \"name\": \"AddColumns\", \"config\": {}, \"children\": [], \"parent\": [4] }, \"7\": { \"id\": 7, \"name\": \"AddColumns\", \"config\": {}, \"children\": [], \"parent\": [5] }, \"8\": { \"id\": 8, \"name\": \"Union\", \"config\": {}, \"children\": [], \"parent\": [6, 7] } } }";

        try (BufferedReader br = new BufferedReader(new FileReader(TestInput.TEST_JSON_INSTRUCTION_INPUT_2))) {
            String line;
            StringBuilder jsonStringInstruction = new StringBuilder();
            InstructionGraph instructionGraph = new InstructionGraph();
            while ((line = br.readLine()) != null) {
                jsonStringInstruction.append(line);
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonStringInstruction.toString()).get("nodes");
            Map<Integer, AbstractInstruction> instructionMap = new HashMap<>();
            // Step 1: Create Instructions and populate the map
            rootNode.fields().forEachRemaining(entry -> {
                JsonNode node = entry.getValue();
                String name = node.get("name").asText();
                int id = node.get("id").asInt();
                Map<String, String> config = new HashMap<>();
                if (node.has("config")) {
                    node.get("config").fields().forEachRemaining(c -> config.put(
                            c.getKey(),
                            c.getValue().asText()
                    ));
                }
                AbstractEtlFactory etlFactory = EtlFactoryPool.getEtlFactory(name);
                AbstractInstruction instruction = etlFactory.createInstruction(config);
                instruction = etlFactory.prepareInstructionGraphData(node, instruction);
                instructionMap.put(id, instruction);
                if(id == INSTRUCTION_ROOT_ID) {
                    instructionGraph.setRoot(instruction);
                }
            });
            // Step 2: Link children to parent nodes
            rootNode.fields().forEachRemaining(entry -> {
                JsonNode node = entry.getValue();
                int id = node.get("id").asInt();
                if (node.has("children") && node.get("children").isArray()) {
                    node.get("children").forEach(child -> {
                        int childId = child.asInt();
                        AbstractInstruction parentInstruction = instructionMap.get(id);
                        AbstractInstruction childInstruction = instructionMap.get(childId);
                        parentInstruction.addChild(childInstruction);
                    });
                }

                if (node.has("parent") && node.get("parent").isArray()) {
                    node.get("parent").forEach(parent -> {
                        int parentId = parent.asInt();
                        AbstractInstruction parentInstruction = instructionMap.get(parentId);
                        AbstractInstruction childInstruction = instructionMap.get(id);
                        childInstruction.addParent(parentInstruction);
                    });
                }
            });
            instructionGraph.setInstructionNodes(new ArrayList<>(instructionMap.values()));
            instructionGraph.setInstructionsMap(instructionMap);
            return instructionGraph;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
