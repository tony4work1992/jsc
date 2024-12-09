package smartosc.jsc.applications.etl.module.mo_filter_columns;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.module.ba_nodes.Executable;
import smartosc.jsc.applications.etl.module.ba_nodes.InputValidation;
import smartosc.jsc.applications.etl.module.ba_nodes.Log;
import smartosc.jsc.applications.etl.module.mo_filter_columns.params.FilterColumnsParamsExtractor;
import smartosc.jsc.applications.etl.module.mo_filter_columns.params.ParamsModel;
import smartosc.jsc.applications.etl.module.mo_filter_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.module.mo_filter_columns.constants.FilterOperator;

public class FilterColumnsExecuter implements Executable {
    public static final String MODULE_NAME = "Filter";

    private InputValidation inputValidation = new InputValidation();
    private Log log = new Log();

    @Override
    public Map<Integer, JsonNode> execute(String params, Map<Integer, JsonNode> listDataSet) throws Exception{
        this.log.logBeforeExecute(MODULE_NAME, params, listDataSet);
        
        FilterColumnsParamsExtractor extractor = new FilterColumnsParamsExtractor();
        ParamsModel filterParams = extractor.extractParams(params);

        
        Integer nodeId = filterParams.getId();

        //execute filter
        JsonNode filterDataSet = filterExecute(filterParams.getConfig(), listDataSet.get(nodeId));

        // set child node dataset value if has child, if not set current node dataset value by new filter dataset
        if(filterParams.getChildren().size() > 0) {
            listDataSet.remove(nodeId);
            for (Integer child : filterParams.getChildren()) {
                listDataSet.put(child, filterDataSet);
            }
        } else {
            listDataSet.put(nodeId, filterDataSet);
        }

        this.log.logAfterExecute(MODULE_NAME, listDataSet);

        return listDataSet;
    }

    public JsonNode filterExecute(List<ColumnModel> filterColumnParams, JsonNode data) throws Exception {
        //case data empty
        if(data.isEmpty()) {
            return data;
        }

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode returnData = mapper.createArrayNode(); // Use ArrayNode for array-like operations

        // Iterate over the input data
        for (JsonNode item : data) {
            if (!item.isObject()) {
                throw new RuntimeException("Data item is not an object");
            }

            boolean shouldAdd = true;
            for (ColumnModel filterColumnParam : filterColumnParams) {
                //validate input
                this.inputValidation.validateInputNotNull(MODULE_NAME, filterColumnParam.getColumn());
                this.inputValidation.validateInputNotNull(MODULE_NAME, filterColumnParam.getOperator());
                this.inputValidation.validateInputNotNull(MODULE_NAME, filterColumnParam.getValue());
                this.inputValidation.validateColumnsExist(MODULE_NAME, new String[] {filterColumnParam.getColumn()}, item);

                if(!applyFilter(filterColumnParam.getOperator(), filterColumnParam.getValue(), item.get(filterColumnParam.getColumn()).asText())){
                    shouldAdd = false;
                    break;
                }
            }

            // Add the item to returnData if it passes all filters
            if (shouldAdd) {
                returnData.add(item);
            }
        }

        return returnData;
    }

    private boolean applyFilter(String operatorFiter, String valueFilter, String dataValue) {
        if (valueFilter.matches("\\d+") && dataValue.matches("\\d+")) {
            return applyIntegerFilter(operatorFiter, Integer.parseInt(valueFilter), Integer.parseInt(dataValue));
        }

        return applyStringFilter(operatorFiter, valueFilter, dataValue);
    }

    private boolean applyIntegerFilter(String operatorFiter, Integer valueFilter, Integer dataValue) {
        return switch (operatorFiter) {
            case FilterOperator.OPERATOR_EQUAL -> dataValue.equals(valueFilter);
            case FilterOperator.OPERATOR_GREATER -> dataValue > valueFilter;
            case FilterOperator.OPERATOR_LESS -> dataValue < valueFilter;
            default -> throw new RuntimeException("Unsupported operatorFiter: " + operatorFiter);
        };
    }

    private boolean applyStringFilter(String operatorFiter, String valueFilter, String dataValue) {
        return switch (operatorFiter) {
            case FilterOperator.OPERATOR_EQUAL -> dataValue.equals(valueFilter);
            case FilterOperator.OPERATOR_CONTAINS -> dataValue.contains(valueFilter);
            default -> throw new RuntimeException("Unsupported operatorFiter: " + operatorFiter);
        };
    }
}
