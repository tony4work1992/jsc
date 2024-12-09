package smartosc.jsc.applications.etl.mo_concat_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ConcatColumnsExtractor;

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

                // Lay danh sach cac column se concat
                String[] columns = concatColumn.getColumns();

                // Lay ten cot moi sau khi thuc hien concat
                String newColumn = concatColumn.getNewColumn();
                String newValue = "";
                for (String column : columns) {
                    // Chi thuc hien concat neu ton tai column
                    if (item.has(column)) {
                        newValue += item.get(column).asText();
                    }
                }  

                ((ObjectNode) item).put(newColumn, newValue);
            }
                
        }
        return returnData;
    }
}