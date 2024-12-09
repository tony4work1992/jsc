package smartosc.jsc.applications.etl.module.mo_rename_columns;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.module.ba_nodes.Executable;
import smartosc.jsc.applications.etl.module.ba_nodes.InputValidation;
import smartosc.jsc.applications.etl.module.ba_nodes.Log;
import smartosc.jsc.applications.etl.module.mo_rename_columns.params.RenameColumnsParamsExtractor;
import smartosc.jsc.applications.etl.module.mo_rename_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.module.mo_rename_columns.params.ParamsModel;

public class RenameColumnsExecuter implements Executable {

    public static final String MODULE_NAME = "Rename";

    private InputValidation inputValidation = new InputValidation();
    private Log log = new Log();

    @Override
    public Map<Integer, JsonNode> execute(String params, Map<Integer, JsonNode> listDataSet) throws Exception {
        this.log.logBeforeExecute(MODULE_NAME, params, listDataSet);
        
        RenameColumnsParamsExtractor extractor = new RenameColumnsParamsExtractor();
        ParamsModel renameParams = extractor.extractParams(params);
        
        Integer nodeId = renameParams.getId();

        // execute rename
        JsonNode renameDataSet = renameExecute(renameParams.getConfig(), listDataSet.get(nodeId));

        // set child node dataset value if has child, if not set current node dataset value by rename add dataset
        if(renameParams.getChildren().size() >0) {
            listDataSet.remove(nodeId);
            for (Integer child : renameParams.getChildren()) {
                listDataSet.put(child, renameDataSet);
            }
        } else {
            listDataSet.put(nodeId, renameDataSet);
        }

        this.log.logAfterExecute(MODULE_NAME, listDataSet);

        return listDataSet;
    }
    
    public JsonNode renameExecute(List<ColumnModel> renameColumnParams, JsonNode data) throws Exception {
        //case data empty
        if(data.isEmpty()) {
            return data;
        }

        JsonNode returnData = data.deepCopy();
        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        
        for (ColumnModel renameColumnParam : renameColumnParams) {

            
            this.inputValidation.validateInputNotNull(MODULE_NAME, renameColumnParam.getColumnToRename());
            this.inputValidation.validateInputNotNull(MODULE_NAME, renameColumnParam.getColumnRenameTo());

            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                //validate input
                this.inputValidation.validateColumnsExist(MODULE_NAME, new String[] {renameColumnParam.getColumnToRename()}, item);

                ((ObjectNode) item).set(renameColumnParam.getColumnRenameTo(), item.get(renameColumnParam.getColumnToRename()));
                ((ObjectNode) item).remove(renameColumnParam.getColumnToRename());
            }
        }

        return returnData;

    }

}
