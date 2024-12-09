package smartosc.jsc.applications.etl.module.mo_load_data.params;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.module.ba_nodes.NodeParamsExtractor;

public class LoadDataParamsExtractor implements NodeParamsExtractor<ParamsModel> {

    public ParamsModel extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        ParamsModel nodeModel = mapper.readValue(params,
                new TypeReference<ParamsModel>() {
                });

        return nodeModel;
    }
}
