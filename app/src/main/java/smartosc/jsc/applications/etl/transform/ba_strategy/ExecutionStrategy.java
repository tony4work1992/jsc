package smartosc.jsc.applications.etl.transform.ba_strategy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.transform.ba_nodes.Executable;

public class ExecutionStrategy {

    private Executable execution;

    public void setExecutionStrategy(Executable execution) {
        this.execution = execution;
    }

    public JsonNode execute(JsonNode params, ArrayNode parentData) throws Exception {
        return this.execution.execute(params.toString(), parentData);
    }
}
