package smartosc.jsc.applications.etl.mo_load_data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
      
import smartosc.jsc.applications.etl.ba_nodes.Executable;

public class LoadDataExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(params);
        } catch (Exception e) {
            System.out.println("Error converting JSON string to JsonNode: " + e.getMessage());
        }
        
        return jsonNode;
    }
   
}