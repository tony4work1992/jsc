package smartosc.jsc.applications.etl.transform.mo_remove_columns.params;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.transform.ba_nodes.NodeParamsExtractor;

public class RemoveColumnsParamsExtrator implements NodeParamsExtractor<List<RemoveColumnsModel>> {

    public List<RemoveColumnsModel> extractParams(String params) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        List<RemoveColumnsModel> convertedParams = mapper.readValue(params,
                new TypeReference<List<RemoveColumnsModel>>() {
        });

        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }

        return convertedParams;
    }
}
