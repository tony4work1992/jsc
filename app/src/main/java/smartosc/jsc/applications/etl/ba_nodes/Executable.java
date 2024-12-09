package smartosc.jsc.applications.etl.ba_nodes;

import com.fasterxml.jackson.databind.JsonNode;

/*
 * Define the interface for the executable class
 */
public interface Executable {

    /*
     * Execute the logics of the ETL node
     */
    public JsonNode execute(String params, JsonNode data) throws Exception;

}
