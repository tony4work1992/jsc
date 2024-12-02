package etl.app.transform.mo_add_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import etl.app.transform.ba_nodes.Executable;
import etl.app.transform.mo_add_columns.params.AddColumnsParamsExtractor;
import etl.app.transform.mo_add_columns.params.ColumnModel;

public class AddColumnsExecutor implements Executable {

    // Add columns to all items in the data set
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        // Lay ra data can them
        AddColumnsParamsExtractor extractor = new AddColumnsParamsExtractor();
        List<ColumnModel> addingColumns = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("[AddColumnsExecutor] Data is not an array");
        }
        // Put data vao tung item trong dataset
        for (ColumnModel column : addingColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("[AddColumnsExecutor] Data item is not an object");
                }
                if (column.getColumn() == null || column.getColumn().isEmpty()) {
                    throw new RuntimeException("[AddColumnsExecutor] Column name is required");
                }
                ((ObjectNode) item).put(column.getColumn(), column.getValue());
            }
        }
        return returnData;

    }

}
