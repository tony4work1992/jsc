package smartosc.jsc.applications.etl.mo_concat_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ConcatColumnsParamsExtractor;

public class ConcatColumnsExcuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ConcatColumnsParamsExtractor extractor = new ConcatColumnsParamsExtractor();
        List<ColumnModel> concatColumns = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (ColumnModel concatColumn : concatColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data is invalid.");
                }
                StringBuilder concatenatedValue = new StringBuilder();
                for (String column : concatColumn.getColumns()) {
                    String value = item.get(column).toString();
                    concatenatedValue.append(value != null ? value.replace("\"", "") : "");
                }
                ((ObjectNode) item).put(concatColumn.getColumnAfterConcat(), concatenatedValue.toString());
            }
        }

        return returnData;
    }
}
