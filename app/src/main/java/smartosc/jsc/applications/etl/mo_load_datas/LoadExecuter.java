package smartosc.jsc.applications.etl.mo_load_datas;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_load_datas.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_load_datas.params.LoadColumnsParamsExtractor;

import java.util.List;

public class LoadExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(params);
    }
}
