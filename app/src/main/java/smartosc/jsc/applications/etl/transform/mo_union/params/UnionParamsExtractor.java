package smartosc.jsc.applications.etl.transform.mo_union.params;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnionParamsExtractor {

    public List<String> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        List<String> convertedParams = mapper.readValue(params,
                new TypeReference<List<String>>() {
        });

        if (convertedParams.isEmpty()) {
            throw new RuntimeException("Parameters are empty");
        }

        return convertedParams;
    }
}
