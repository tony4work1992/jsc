package smartosc.jsc.applications.etl.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnionNodeConfig {
    public JsonNode convertParams(String jsonData) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonData);

        return jsonNode;
    }
}