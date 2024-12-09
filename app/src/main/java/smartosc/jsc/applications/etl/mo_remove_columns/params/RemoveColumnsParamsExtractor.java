package smartosc.jsc.applications.etl.mo_remove_columns.params;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;

import java.util.List;

public class RemoveColumnsParamsExtractor implements NodeParamsExtractor<List<ColumnModel>> {
    public List<ColumnModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<ColumnModel> convertedParams = mapper.readValue(params,
                new TypeReference<List<ColumnModel>>() {
                });

        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }

        for (ColumnModel columnModel : convertedParams) {
            if (columnModel.getColumn().isEmpty()) {
                throw new RuntimeException("Input Remove Columns Empty");
            }
        }

        return convertedParams;
    }
}
