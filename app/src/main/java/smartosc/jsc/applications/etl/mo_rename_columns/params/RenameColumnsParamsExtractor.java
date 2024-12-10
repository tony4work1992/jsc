package smartosc.jsc.applications.etl.mo_rename_columns.params;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.ba_nodes.NodeParamsExtractor;

public class RenameColumnsParamsExtractor implements NodeParamsExtractor<List<ColumnModel>> {

    public List<ColumnModel> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(params);

        List<ColumnModel> renameColumnModels = new ArrayList<>();
        for (JsonNode node : rootNode) {
            String currentColumnName = node.get("currentColumnName").asText();
            String newColumnName = node.get("newColumnName").asText();
            renameColumnModels.add(new ColumnModel(currentColumnName, newColumnName));
        }
        return renameColumnModels;
    }
}
