package smartosc.jsc.applications.etl.mo_nodes.concat.params;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;
import smartosc.jsc.applications.etl.mo_nodes.concat.params.ColumnModel;

import java.util.List;

public class ConcatParamExtractor implements NodeParamsExtractor<List<ColumnModel>> {

    public List<ColumnModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<ColumnModel> convertedParams = mapper.readValue(
                params,
                new TypeReference<List<ColumnModel>>() {}
        );

        for (ColumnModel columnModel : convertedParams) {
            if (columnModel.getColumnValue() == null || columnModel.getColumnValue().isEmpty()) {
                throw new RuntimeException("Column value is empty");
            }
        }

        return convertedParams;
    }
}
