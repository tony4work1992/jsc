package smartosc.jsc.applications.etl.mo_nodes.concat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.ba_nodes.Node;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.mo_nodes.concat.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_nodes.concat.params.ConcatParamExtractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcatNode implements Node {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ConcatParamExtractor extractor = new ConcatParamExtractor();
        List<ColumnModel> concatParams = extractor.extractParams(params);

        List<String> updatedData =  new ArrayList<>();
        JsonNode dataset = data.deepCopy();

        if (!dataset.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (JsonNode item : dataset) {
            for (JsonNode row : item) {
                for (ColumnModel concatParam : concatParams) {
                    List<String> columnValue = Arrays.asList(concatParam.getColumnValue().split(","));
                    StringBuilder newValue = new StringBuilder();

                    for (String column : columnValue) {
                        if (!newValue.isEmpty()) {
                            newValue.append("_");
                        }

                        newValue.append(row.get(column).asText());
                    }

                    ((ObjectNode) row).put(concatParam.getNewColumn(), newValue.toString());
                }

                updatedData.add(row.toString());
            }
        }

        return mapper.readTree(updatedData.toString());
    }
}
