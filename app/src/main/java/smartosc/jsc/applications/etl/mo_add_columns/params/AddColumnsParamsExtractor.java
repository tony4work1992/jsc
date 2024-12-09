package smartosc.jsc.applications.etl.mo_add_columns.params;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;

public class AddColumnsParamsExtractor implements NodeParamsExtractor<List<ColumnModel>> {

    // Lay ra tung cot
    public List<ColumnModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<ColumnModel> convertedParams = mapper.readValue(params,
                new TypeReference<List<ColumnModel>>() {
        });
        if (convertedParams.isEmpty()) {
            throw new RuntimeException("[AddColumnsParamsExtractor] Parameters are empty");
        }
        System.out.println("[AddColumnsParamsExtractor] Parameters: " + convertedParams);
        return convertedParams;

    }

}
