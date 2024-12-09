package smartosc.jsc.applications.etl.module.core_nodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.module.ba_nodes.Executable;

public class NodeExecute {
    private static final String OPTIONS_INPUT_PATH = System.getProperty("user.dir") + "/app/src/main/java/smartosc/jsc/applications/etl/config/input.json";
    private static final String[] MODULE_SUPPORT = {"loadData", "filter", "add", "remove","concat", "rename","union"};
    private static final String KEY_INPUT = "input";
    private static final String KEY_OPTION = "option";
    private static final String KEY_ID = "id";
    private static final String KEY_PARENT = "parent";
    private static final String KEY_CONFIG = "config";
    private static final String KEY_CHILDREN = "children";
    private static final String[] REQUIRED_KEY_INPUT = {KEY_OPTION, KEY_INPUT};
    private static final String[] REQUIRED_KEY_IN_INPUT_KEY = {KEY_ID, KEY_CONFIG, KEY_CHILDREN, KEY_PARENT};

    public Map<Executable, String> executeNode() throws Exception 
    {
        try (Scanner scanner = new Scanner(new File(OPTIONS_INPUT_PATH))) {
            ObjectMapper mapper = new ObjectMapper();
            String optionLine = "";
            while (scanner.hasNextLine()) {
                optionLine += scanner.nextLine().trim();
                
            }
            JsonNode input = mapper.readTree(optionLine);

            return sortOrderNode(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Options file not found: " + OPTIONS_INPUT_PATH, e);
        }
    }

    private Map<Executable, String> sortOrderNode(JsonNode input) throws Exception {
        Set<Integer> addedNodes = new HashSet<>();
        Map<Executable, String> sortedOrderNode = new LinkedHashMap<>();
        int maxIterations = input.size(); // To handle potential infinite loops
        int iterationCount = 0;
    
        while (sortedOrderNode.size() < input.size()) {
            if (iterationCount++ > maxIterations) {
                throw new RuntimeException("Potential cyclic dependency detected in input data.");
            }
    
            boolean progressMade = false;
    
            for (JsonNode node : input) {
                if (!isValidInput(node)) {
                    throw new RuntimeException("Invalid input format: " + node);
                }
    
                String option = node.get(KEY_OPTION).asText();
                if (!isModuleSupported(option)) {
                    throw new RuntimeException("Unsupported module option: " + option);
                }
    
                JsonNode inputNode = node.get(KEY_INPUT);
                JsonNode parentNode = inputNode.get(KEY_PARENT);
                int nodeId = inputNode.get(KEY_ID).asInt();
    
                if (addedNodes.contains(nodeId)) {
                    continue;
                }
    
                if (isParentAdded(addedNodes, parentNode)) {
                    Executable module = new ModuleFactory().getModuleExecuter(option);
                    sortedOrderNode.put(module, inputNode.toString());
                    addedNodes.add(nodeId);
                    progressMade = true;
                }
            }
    
            if (!progressMade) {
                throw new RuntimeException("Unresolvable dependencies in input data.");
            }
        }
    
        return sortedOrderNode;
    }
    
    private boolean isParentAdded(Set<Integer> addedNodes, JsonNode parentNode) {
        if (parentNode.isNull() || (parentNode.isArray() && parentNode.size() == 0)) {
            return true;
        }
    
        for (JsonNode parent : parentNode) {
            if (!addedNodes.contains(parent.asInt())) {
                return false;
            }
        }
    
        return true;
    }
    
    private boolean isModuleSupported(String option) {
        return Arrays.asList(MODULE_SUPPORT).contains(option);
    }
    
    private boolean isValidInput(JsonNode input) {
        if (input == null || !input.isObject()) {
            return false;
        }
    
        for (String key : REQUIRED_KEY_INPUT) {
            if (!input.has(key)) {
                return false;
            }
        }
    
        JsonNode inputNode = input.get(KEY_INPUT);
        if (inputNode == null || inputNode.isNull()) {
            return false;
        }
    
        for (String key : REQUIRED_KEY_IN_INPUT_KEY) {
            if (!inputNode.has(key)) {
                return false;
            }
        }
    
        return true;
    }
}
