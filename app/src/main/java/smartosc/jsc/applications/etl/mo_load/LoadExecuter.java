package smartosc.jsc.applications.etl.mo_load;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_load.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_load.params.LoadParamsExtractor;

public class LoadExecuter implements Executable{

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        LoadParamsExtractor extractor = new LoadParamsExtractor();
        List<ColumnModel> dataset = extractor.extractParams(params);
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode returnData = objectMapper.valueToTree(dataset);
        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
      
        return returnData;
    }

}
