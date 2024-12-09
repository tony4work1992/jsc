package smartosc.jsc.applications.etl.mo_concat_columns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ConcatColumnsParamsExtractor;

import java.util.List;

public class ConcatColumnsExecute implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode dataSet) throws Exception {
        ConcatColumnsParamsExtractor concatColumnsParamsExtractor = new ConcatColumnsParamsExtractor();
        List<ColumnModel> concatColumns = concatColumnsParamsExtractor.extractParams(params);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode returnData = dataSet.deepCopy();

        // Check Data
        if (!dataSet.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (ColumnModel column : concatColumns) {
            for (JsonNode item : returnData) {
                // check item data
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                String[] listColumn = column.getListColumn().split(",");    // get list cac cot se concat
                StringBuilder newValue = new StringBuilder("\"");
                for (int i = 0; i < listColumn.length; i++) {
                    if (item.has(listColumn[i])) {
                        newValue.append(item.get(listColumn[i]).asText());
                    }
                    if (i < listColumn.length - 1) {
                        newValue.append("_");
                    }
                }

                // kiem tra xem column can concat da co chua
                if (item.has(column.getColumn())) {
                    ((ObjectNode) item).remove(column.getColumn());         // neu co r se xoa di
                }

                ((ObjectNode) item).set(column.getColumn(), objectMapper.readTree(newValue.append("\"").toString()));
                // Them column moi vao
            }
        }
        return returnData;
    }
}
