package smartosc.jsc.applications.etl.transform.mo_filter.params;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.transform.ba_nodes.NodeParamsExtractor;

public class FilterParamsExtractor implements NodeParamsExtractor<List<FilterModel>> {

    public List<FilterModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<FilterModel> convertedParams = mapper.readValue(params,
                new TypeReference<List<FilterModel>>() {
        });
        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }
        return convertedParams;
    }

}
