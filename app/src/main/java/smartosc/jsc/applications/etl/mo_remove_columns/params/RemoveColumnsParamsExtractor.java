package smartosc.jsc.applications.etl.mo_remove_columns.params;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RemoveColumnsParamsExtractor {

    public List<String> extractParams(String params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(params);

        List<String> columnsToRemove = new ArrayList<>();
        for (JsonNode node : rootNode) {
            String columnName = node.asText();
            columnsToRemove.add(columnName);
        }
        return columnsToRemove;
    }
}
