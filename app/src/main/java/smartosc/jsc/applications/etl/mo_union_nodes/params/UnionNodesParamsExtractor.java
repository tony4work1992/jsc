package smartosc.jsc.applications.etl.mo_union_nodes.params;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class UnionNodesParamsExtractor {

    public ArrayNode extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode convertedParams = (ArrayNode) mapper.readTree(params);

        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }
        return convertedParams;

    }
}
