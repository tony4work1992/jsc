package smartosc.jsc.applications.etl.mo_loader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Loader {
    public void load(JsonNode dataset) throws Exception {
        System.out.println("=======Transformed dataset=======");
        ObjectMapper mapper = new ObjectMapper();
        String updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataset);
        System.out.println(updatedJson);
    }
}
