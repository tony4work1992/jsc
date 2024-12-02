package smartosc.jsc.applications.etl.mo_concat_columns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ConcatColumnsExtractor;

import java.util.List;

public class ConcatColumnsExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode dataset) throws Exception {
        ConcatColumnsExtractor extractor = new ConcatColumnsExtractor();
        List<ColumnModel> concatColumns = extractor.extractParams(params);

        JsonNode returnData = dataset.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (ColumnModel concatColumn : concatColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                String[] columns = concatColumn.getCurrentColumns();

                String newColumn = concatColumn.getNewColumn();
                String newValue = "User: ";
                for (String column : columns) {
                    if (item.has(column)) {
                        newValue += " - " + item.get(column).asText();
                    } else {
                        throw new RuntimeException(column + " is not found");
                    }
                }

                ((ObjectNode) item).put(newColumn, newValue);
            }

        }
        return returnData;
    }

}
