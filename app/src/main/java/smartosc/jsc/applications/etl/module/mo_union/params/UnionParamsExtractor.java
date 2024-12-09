package smartosc.jsc.applications.etl.module.mo_union.params;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.module.ba_nodes.NodeParamsExtractor;

public class UnionParamsExtractor implements NodeParamsExtractor<ParamsModel> {

    public ParamsModel extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        ParamsModel nodeModel = mapper.readValue(params,
                new TypeReference<ParamsModel>() {
                });

        return nodeModel;
    }
}
