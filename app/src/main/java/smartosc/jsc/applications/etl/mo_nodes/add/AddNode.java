package smartosc.jsc.applications.etl.mo_nodes.add;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Node;
import smartosc.jsc.applications.etl.mo_nodes.add.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_nodes.add.params.AddParamExtractor;

public class AddNode implements Node {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AddParamExtractor extractor = new AddParamExtractor();
        List<ColumnModel> addParams = extractor.extractParams(params);

        JsonNode dataset = data.deepCopy();
        List<String> updatedData = new ArrayList<>();

        if (!dataset.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (JsonNode item : dataset) {
            for (JsonNode row : item) {
                for (ColumnModel addParam : addParams) {
                    ((ObjectNode) row).put(addParam.getColumn(), addParam.getValue());
                }

                updatedData.add(row.toString());
            }
        }

        return mapper.readTree(updatedData.toString());
    }
}
