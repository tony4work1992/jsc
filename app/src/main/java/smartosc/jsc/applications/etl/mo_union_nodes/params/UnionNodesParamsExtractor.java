package smartosc.jsc.applications.etl.mo_union_nodes.params;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;

import java.util.List;

public class UnionNodesParamsExtractor implements NodeParamsExtractor<List<NodeModel>> {
    public List<NodeModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<NodeModel> convertedParams = mapper.readValue(params,
                new TypeReference<List<NodeModel>>() {
                });

        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }
        return convertedParams;

    }

}
