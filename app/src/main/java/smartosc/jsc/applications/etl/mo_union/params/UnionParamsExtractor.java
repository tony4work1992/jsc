package smartosc.jsc.applications.etl.mo_union.params;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;

public class UnionParamsExtractor implements NodeParamsExtractor<List<UnionModel>> {

    public List<UnionModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<UnionModel> convertedParams = mapper.readValue(params,
                new TypeReference<List<UnionModel>>() {
        });
        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }
        System.out.println("[UnionParamsExtractor] Parameters: " + convertedParams);
        return convertedParams;
    }

}
