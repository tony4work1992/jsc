package smartosc.jsc.applications.etl.mo_concat_values;

import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_concat_values.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_concat_values.params.ConcatValuesParamsExtractor;

public class ConcatValuesExecuter implements Executable{
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ConcatValuesParamsExtractor extractor = new ConcatValuesParamsExtractor();
        List<ColumnModel> concatParams = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        ObjectMapper objectMapper = new ObjectMapper();

        // Tạo mảng mới để lưu các đối tượng đã được concat
        ArrayNode concatenatedArrayNode = objectMapper.createArrayNode();

        for(ColumnModel concatColumn : concatParams) {
            // Lặp qua từng phần tử trong mảng
            for (JsonNode node : returnData) {
                String concatValue = this.getValueColumn(concatColumn.getColumn(), node);

//                // Tạo một đối tượng mới với trường "fullNameCity"
                ((ObjectNode) node).put(concatColumn.getNewColumn(), concatValue);

//                // Thêm đối tượng đã được concat vào mảng mới
                concatenatedArrayNode.add(node);
            }
        }

        return objectMapper.readTree(concatenatedArrayNode.toString());
    }

    private String getValueColumn(String columns, JsonNode node) {
        List<String> concatColumnArray = Arrays.asList(columns.split(","));
        String concatValue = "";
        for (String column: concatColumnArray) {
            if (!node.has(column)) {
                throw new RuntimeException("Data have not column " + column);
            }

            concatValue += " " + node.get(column).asText();
        }

        return concatValue;

    }
}
