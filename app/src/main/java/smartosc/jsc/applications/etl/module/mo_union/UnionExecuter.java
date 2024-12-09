package smartosc.jsc.applications.etl.module.mo_union;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.module.ba_nodes.Executable;
import smartosc.jsc.applications.etl.module.ba_nodes.Log;
import smartosc.jsc.applications.etl.module.mo_union.params.UnionParamsExtractor;
import smartosc.jsc.applications.etl.module.mo_union.params.ParamsModel;

public class UnionExecuter implements Executable {

    public static final String MODULE_NAME = "Union";

    private Log log = new Log();

    @Override
    public Map<Integer, JsonNode> execute(String params, Map<Integer, JsonNode> listDataSet) throws Exception {
        this.log.logBeforeExecute(MODULE_NAME, params, listDataSet);
        UnionParamsExtractor extractor = new UnionParamsExtractor();
        ParamsModel unionParams = extractor.extractParams(params);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode unionNode = mapper.createArrayNode();
        
        if(unionParams.getParent().size() < 2) {
            throw new RuntimeException("Data item is not an object");
        }

        // union parent into union data set and remove parent data set in list data set
        for (Integer parent : unionParams.getParent()) {
            JsonNode dataSetParent = listDataSet.get(parent);
            if (dataSetParent.isArray()) {
                dataSetParent.forEach(unionNode::add);
                listDataSet.remove(parent);
            }
        }

        // add union data set into list data set
        listDataSet.put(unionParams.getId(), unionNode);
        
        
        this.log.logAfterExecute(MODULE_NAME, listDataSet);
        
        return listDataSet;

    }
}
