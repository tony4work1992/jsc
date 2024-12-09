package smartosc.jsc.applications.etl.module.ba_nodes;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public interface Executable {
    /*
     * Execute the logics of the ETL node
     */
    public Map<Integer, JsonNode> execute(String params, Map<Integer, JsonNode> listDataSet) throws Exception;
}
