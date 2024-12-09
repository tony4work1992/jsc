package smartosc.jsc.applications.etl.module.mo_concat_columns;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.module.ba_nodes.Executable;
import smartosc.jsc.applications.etl.module.ba_nodes.InputValidation;
import smartosc.jsc.applications.etl.module.ba_nodes.Log;
import smartosc.jsc.applications.etl.module.mo_concat_columns.params.ParamsModel;
import smartosc.jsc.applications.etl.module.mo_concat_columns.params.ConcatColumnsParamsExtractor;
import smartosc.jsc.applications.etl.module.mo_concat_columns.params.ColumnModel;

public class ConcatColumnsExecuter implements Executable {

    public static final String MODULE_NAME = "Concat";

    private InputValidation inputValidation = new InputValidation();
    private Log log = new Log();

    @Override
    public Map<Integer, JsonNode> execute(String params, Map<Integer, JsonNode> listDataSet) throws Exception {
        this.log.logBeforeExecute(MODULE_NAME, params, listDataSet);
        
        ConcatColumnsParamsExtractor extractor = new ConcatColumnsParamsExtractor();
        ParamsModel concatParams = extractor.extractParams(params);
        
        Integer nodeId = concatParams.getId();

        //execute concat
        JsonNode concatDataSet = concatExecute(concatParams.getConfig(), listDataSet.get(nodeId));

        // set child node dataset value if has child, if not set current node dataset value by new concat dataset
        if(concatParams.getChildren().size() >0) {
            listDataSet.remove(nodeId);
            for (Integer child : concatParams.getChildren()) {
                listDataSet.put(child, concatDataSet);
            }
        } else {
            listDataSet.put(nodeId, concatDataSet);
        }

        this.log.logAfterExecute(MODULE_NAME, listDataSet);

        return listDataSet;
    }
    
    public JsonNode concatExecute(List<ColumnModel> concatColumnParams, JsonNode data) throws Exception {
        //case data empty
        if(data.isEmpty()) {
            return data;
        }
        
        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        
        for (ColumnModel concatColumnParam : concatColumnParams) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                this.inputValidation.validateInputNotNull(MODULE_NAME, concatColumnParam.getNewColumnName());
                this.inputValidation.validateInputNotEmpty(MODULE_NAME, concatColumnParam.getColumns());
                this.inputValidation.validateColumnsExist(MODULE_NAME, concatColumnParam.getColumns(), item);

                String value = "";

                for(String column : concatColumnParam.getColumns()){
                    value += item.get(column).asText() + " ";
                }

                ((ObjectNode) item).put(concatColumnParam.getNewColumnName(), value.trim());
            }
        }

        return returnData;

    }

}
