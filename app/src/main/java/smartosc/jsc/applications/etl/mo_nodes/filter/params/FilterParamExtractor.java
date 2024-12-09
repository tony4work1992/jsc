package smartosc.jsc.applications.etl.mo_nodes.filter.params;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;

import java.util.List;

public class FilterParamExtractor implements NodeParamsExtractor<List<ColumnModel>> {

    public List<ColumnModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<ColumnModel> jsonParams = mapper.readValue(
                params,
                new TypeReference<List<ColumnModel>>() {}
        );

        for (ColumnModel jsonParam : jsonParams) {
            if (jsonParam.getColumn().isEmpty()) {
                throw new RuntimeException("Filter column is empty");
            }

            if (jsonParam.getOperator().isEmpty()) {
                throw new RuntimeException("Filter operator is empty");
            }
        }

        return jsonParams;
    }
}
