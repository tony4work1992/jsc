package smartosc.jsc.applications.etl.ba_nodes;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public class BaseExecute {
    ExecuteFactory executeFactory;

    public BaseExecute(ExecuteFactory executeFactory) {
        this.executeFactory = executeFactory;
    }

    public JsonNode execute(String executeName, String params, JsonNode dataSet, Map<Integer, JsonNode> result) throws Exception {
        try {
            if (params.isEmpty()) {
                throw new RuntimeException("Input Params Is Empty");
            }
            if (executeName.isEmpty()) {
                throw new RuntimeException("Execute " + executeName + " Is Empty");
            }
            Executable executor = this.executeFactory.create(executeName, result);
            if (executor == null) {
                throw new RuntimeException("Execute " + executeName + " Is Not Found");
            }
            return executor.execute(params, dataSet);
        } catch (Exception e) {
            throw new RuntimeException("Error executing " + executeName, e);
        }
    }
}