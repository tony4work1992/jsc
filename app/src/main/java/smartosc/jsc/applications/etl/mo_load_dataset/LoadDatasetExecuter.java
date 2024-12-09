package smartosc.jsc.applications.etl.mo_load_dataset;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.ba_nodes.Executable;

public class LoadDatasetExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode dataset) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(params);
    }

}
