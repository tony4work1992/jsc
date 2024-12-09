package smartosc.jsc.applications.etl.mo_concat.params;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;

public class ConcatParamsExtractor implements NodeParamsExtractor<List<ConcatModel>> {

    public List<ConcatModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<ConcatModel> convertedParams = mapper.readValue(params,
                new TypeReference<List<ConcatModel>>() {
        });
        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }
        System.out.println("[ConcatParamsExtractor] Parameters: " + convertedParams);
        return convertedParams;

    }

}
