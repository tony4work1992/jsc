package smartosc.jsc.applications.etl.module.mo_remove_columns.params;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.module.ba_nodes.NodeParamsExtractor;

public class RemoveColumnsParamsExtractor implements NodeParamsExtractor<ParamsModel> {

    public ParamsModel extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        ParamsModel convertedParams = mapper.readValue(params,
                new TypeReference<ParamsModel>() {
                });

        return convertedParams;

    }

}

