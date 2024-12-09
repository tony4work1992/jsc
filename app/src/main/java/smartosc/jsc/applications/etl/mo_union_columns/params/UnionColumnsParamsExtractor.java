package smartosc.jsc.applications.etl.mo_union_columns.params;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;

public class UnionColumnsParamsExtractor implements NodeParamsExtractor<List<ColumnModel>> {

    @Override
    public List<ColumnModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<ColumnModel> convertedParams = mapper.readValue(params,
                new TypeReference<List<ColumnModel>>() {
                });

        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }

        for (ColumnModel columnModel : convertedParams) {
            if (columnModel.getNodeIds().isEmpty()) {
                throw new RuntimeException("Input Union Columns Empty");
            }
        }

        return convertedParams;
    }
}