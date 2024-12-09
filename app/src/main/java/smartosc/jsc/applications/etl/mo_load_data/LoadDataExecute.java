package smartosc.jsc.applications.etl.mo_load_data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_load_data.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_load_data.params.LoadColumnsParamsExtractor;

import java.util.List;

public class LoadDataExecute implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        LoadColumnsParamsExtractor extractor = new LoadColumnsParamsExtractor();
        List<ColumnModel> dataset = extractor.extractParams(params);
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode returnData = objectMapper.valueToTree(dataset);
        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        return returnData;
    }
}