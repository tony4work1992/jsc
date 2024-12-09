package smartosc.jsc.applications.etl.transform.mo_load_data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.transform.ba_nodes.Executable;

public class LoadDataExecutor implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(params);
    }
}
