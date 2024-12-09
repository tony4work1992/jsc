package smartosc.jsc.applications.etl.module.mo_add_columns;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.module.ba_nodes.Executable;
import smartosc.jsc.applications.etl.module.ba_nodes.InputValidation;
import smartosc.jsc.applications.etl.module.ba_nodes.Log;
import smartosc.jsc.applications.etl.module.mo_add_columns.params.AddColumnsParamsExtractor;
import smartosc.jsc.applications.etl.module.mo_add_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.module.mo_add_columns.params.ParamsModel;

public class AddColumnsExecuter implements Executable {

    public static final String MODULE_NAME = "Add";

    private InputValidation inputValidation = new InputValidation();
    private Log log = new Log();

    @Override
    public Map<Integer, JsonNode> execute(String params, Map<Integer, JsonNode> listDataSet) throws Exception {
        this.log.logBeforeExecute(MODULE_NAME, params, listDataSet);

        AddColumnsParamsExtractor extractor = new AddColumnsParamsExtractor();
        ParamsModel addParams = extractor.extractParams(params);
        
        Integer nodeId = addParams.getId();

        // add execute
        JsonNode addDataSet = addExecute(addParams.getConfig(), listDataSet.get(nodeId));

        // set child node dataset value if has child, if not set current node dataset value by new add dataset
        if(addParams.getChildren().size() >0) {
            listDataSet.remove(nodeId);
            for (Integer child : addParams.getChildren()) {
                listDataSet.put(child, addDataSet);
            }
        } else {
            listDataSet.put(nodeId, addDataSet);
        }

        this.log.logAfterExecute(MODULE_NAME, listDataSet);

        return listDataSet;
    }

    public JsonNode addExecute(List<ColumnModel> addColumnParams, JsonNode data) throws Exception {
        //case data empty
        if(data.isEmpty()) {
            return data;
        }
        
        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (ColumnModel addColumnParam : addColumnParams) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                //validate input
                this.inputValidation.validateInputNotNull(MODULE_NAME, addColumnParam.getColumn());
                
                ((ObjectNode) item).put(addColumnParam.getColumn(), addColumnParam.getValue());
            }
        }

        return returnData;
    }

}
