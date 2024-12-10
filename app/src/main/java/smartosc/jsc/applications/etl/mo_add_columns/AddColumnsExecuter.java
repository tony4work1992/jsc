package smartosc.jsc.applications.etl.mo_add_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_add_columns.params.AddColumnsParamsExtractor;
import smartosc.jsc.applications.etl.mo_add_columns.params.ColumnModel;

public class AddColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        AddColumnsParamsExtractor extractor = new AddColumnsParamsExtractor();
        List<ColumnModel> addingColumns = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        for (ColumnModel column : addingColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }
                ((ObjectNode) item).put(column.getColumn(), column.getValue());
            }
        }
        return returnData;

    }

}
