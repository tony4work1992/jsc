package smartosc.jsc.applications.etl.module.mo_remove_columns;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.module.ba_nodes.Executable;
import smartosc.jsc.applications.etl.module.ba_nodes.InputValidation;
import smartosc.jsc.applications.etl.module.ba_nodes.Log;
import smartosc.jsc.applications.etl.module.mo_remove_columns.params.RemoveColumnsParamsExtractor;
import smartosc.jsc.applications.etl.module.mo_remove_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.module.mo_remove_columns.params.ParamsModel;

public class RemoveColumnsExecuter implements Executable {

    public static final String MODULE_NAME = "Remove";

    private InputValidation inputValidation = new InputValidation();
    private Log log = new Log();

    @Override
    public Map<Integer, JsonNode> execute(String params, Map<Integer, JsonNode> listDataSet) throws Exception {
        this.log.logBeforeExecute(MODULE_NAME, params, listDataSet);

        RemoveColumnsParamsExtractor extractor = new RemoveColumnsParamsExtractor();
        ParamsModel removeParams = extractor.extractParams(params);
        
        Integer nodeId = removeParams.getId();

        // execute remove
        JsonNode removeDataSet = removeExecute(removeParams.getConfig(), listDataSet.get(nodeId));

        // set child node dataset value if has child, if not set current node dataset value by new remove dataset
        if(removeParams.getChildren().size() >0) {
            listDataSet.remove(nodeId);
            for (Integer child : removeParams.getChildren()) {
                listDataSet.put(child, removeDataSet);
            }
        } else {
            listDataSet.put(nodeId, removeDataSet);
        }

        this.log.logAfterExecute(MODULE_NAME, listDataSet);

        return listDataSet;
    }
    
    public JsonNode removeExecute(List<ColumnModel> removeColumnParams, JsonNode data) throws Exception {
        //case data empty
        if(data.isEmpty()) {
            return data;
        }
        
        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (ColumnModel removeColumnParam : removeColumnParams) {
            
            //validate input
            this.inputValidation.validateInputNotEmpty(MODULE_NAME, removeColumnParam.getColumns());
            
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                //validate input
                this.inputValidation.validateColumnsExist(MODULE_NAME, removeColumnParam.getColumns(), item);

                for (String column : removeColumnParam.getColumns()) {
                    ((ObjectNode) item).remove(column);
                }
            }
        }

        return returnData;
    }

}
