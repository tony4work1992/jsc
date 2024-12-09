package smartosc.jsc.applications.etl.mo_extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Extractor {
    public JsonNode extractData(String dataset) throws Exception {
        System.out.println("=======Original dataset=======");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.readTree(dataset);
        if (!jsonData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonData));
        return jsonData;
    }
}
