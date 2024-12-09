package smartosc.jsc.applications.etl.mo_concat_value;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_concat_value.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_concat_value.params.ConcatValueParamsExtractor;

public class ConcatValueExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ConcatValueParamsExtractor extractor = new ConcatValueParamsExtractor();
        List<ColumnModel> concatValueColumns = extractor.extractParams(params);

        ObjectMapper objectMapper = new ObjectMapper();

        if (!data.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        for (JsonNode item : data) {
            for (ColumnModel column : concatValueColumns) {
                if (!item.isObject()) {
                    throw new RuntimeException("Data item is not an object");
                }

                String concatValue = this.getConcatValue(column.getConcatColumn(), item);

                ((ObjectNode) item).put(column.getNewColumn(), concatValue);
            }
            
        }
        return data;
    }

    private String getConcatValue(String concatColumns, JsonNode item) {
        List<String> concatColumnArray = Arrays.asList(concatColumns.split(","));
        String concatValue = "";
        for (String column: concatColumnArray) {
            if (!item.has(column)) {
                throw new RuntimeException("Concat: Column " + column + " does not exists");
            }
            concatValue += " " + item.get(column).asText();
        }

        return concatValue;
    }
}
