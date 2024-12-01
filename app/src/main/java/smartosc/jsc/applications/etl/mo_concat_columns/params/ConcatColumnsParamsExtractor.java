package smartosc.jsc.applications.etl.mo_concat_columns.params;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConcatColumnsParamsExtractor {

    public List<ColumnModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<ColumnModel> convertedParams = mapper.readValue(params, new TypeReference<>() {});

        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }

        return convertedParams;
    }
}
