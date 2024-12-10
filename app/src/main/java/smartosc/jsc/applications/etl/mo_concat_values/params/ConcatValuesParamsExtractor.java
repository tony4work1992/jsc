package smartosc.jsc.applications.etl.mo_concat_values.params;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConcatValuesParamsExtractor {
    public List<ColumnModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<ColumnModel> convertedParams = mapper.readValue(params,
                new TypeReference<List<ColumnModel>>() {
                });

        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }
        return convertedParams;
    }
}
