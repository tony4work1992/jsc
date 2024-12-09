package smartosc.jsc.applications.etl.mo_nodes.load;

import com.fasterxml.jackson.databind.JsonNode;
import smartosc.jsc.applications.etl.ba_nodes.Node;

public class LoadNode implements Node {

    @Override
    public JsonNode execute(String params, JsonNode data) {
        return data.deepCopy().get(0);
    }
}
