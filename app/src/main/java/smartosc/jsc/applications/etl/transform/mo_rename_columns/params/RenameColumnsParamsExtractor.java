package smartosc.jsc.applications.etl.transform.mo_rename_columns.params;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.transform.ba_nodes.NodeParamsExtractor;

public class RenameColumnsParamsExtractor implements NodeParamsExtractor<List<RenameColumnModel>> {

    // Lay ra tung cot
    public List<RenameColumnModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<RenameColumnModel> convertedParams = mapper.readValue(params,
                new TypeReference<List<RenameColumnModel>>() {
        });

        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }

        return convertedParams;
    }

}
