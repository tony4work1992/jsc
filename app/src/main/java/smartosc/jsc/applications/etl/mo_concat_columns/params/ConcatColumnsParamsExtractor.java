package smartosc.jsc.applications.etl.mo_concat_columns.params;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;

public class ConcatColumnsParamsExtractor implements NodeParamsExtractor<List<ColumnModel>> {

    public List<ColumnModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(params);

        List<ColumnModel> columnModels = new ArrayList<>();
        for (JsonNode node : rootNode) {
            String newColumnName = node.get("newColumnName").asText();
            List<String> sourceColumns = new ArrayList<>();
            for (JsonNode column : node.get("sourceColumns")) {
                sourceColumns.add(column.asText());
            }
            columnModels.add(new ColumnModel(newColumnName, sourceColumns));
        }
        return columnModels;
    }

}