package smartosc.jsc.applications.etl.mo_filter_values.params;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;

public class FilterValuesParamsExtractor implements NodeParamsExtractor<List<ColumnModel>> {
    public List<ColumnModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<ColumnModel> convertedParams = mapper.readValue(params,
                new TypeReference<List<ColumnModel>>() {
                });

        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }
        for (ColumnModel column : convertedParams) {
            if (column.getColumnName().isEmpty()) {
                throw new RuntimeException("Param columnName is empty");
            }

            if (column.getCondition().isEmpty()) {
                throw new RuntimeException("Param condition is empty");
            }

            if (column.getValue().isEmpty()) {
                throw new RuntimeException("Param value is empty");
            }
        }
        return convertedParams;
    }
}
