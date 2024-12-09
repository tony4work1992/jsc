package smartosc.jsc.applications.etl.ba_nodes;

import com.fasterxml.jackson.databind.JsonNode;

public interface DatasetsExecutable {
    public JsonNode execute(JsonNode json1, JsonNode json2) throws Exception;
}
