package smartosc.jsc.applications.etl.mo_concat_columns;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ConcatColumnsParamsExtractor;

import java.util.List;

public class ConcatColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ConcatColumnsParamsExtractor extractor = new ConcatColumnsParamsExtractor();
        List<ColumnModel> concatColumns = extractor.extractParams(params);
        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        for (ColumnModel column : concatColumns) {
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }
                StringBuilder concatColumnValue = new StringBuilder();
                for (String columnName : column.getConcatColumns()) {
                    if(!item.has(columnName)){
                        throw new RuntimeException("This column " + columnName + " doesn't exist.");
                    }
                    String columnValue = item.get(columnName)!=null? item.get(columnName).textValue():"";
                    concatColumnValue.append(columnValue);
                }

                ((ObjectNode) item).put(column.getConcatColumnName(), concatColumnValue.toString());
            }
        }
        return returnData;

    }

}
