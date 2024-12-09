package smartosc.jsc.applications.etl.mo_nodes.rename;

import smartosc.jsc.applications.etl.ba_nodes.Node;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.mo_nodes.rename.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_nodes.rename.params.RenameParamExtractor;

import java.util.ArrayList;
import java.util.List;

public class RenameNode implements Node {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        RenameParamExtractor extractor = new RenameParamExtractor();
        List<ColumnModel> renameColumns = extractor.extractParams(params);

        JsonNode datasets = data.deepCopy();
        List<String> updatedData =  new ArrayList<>();

        for (JsonNode dataset: datasets) {
            ObjectNode updateItem = mapper.createObjectNode();

            for (JsonNode item : dataset) {
                item.fields().forEachRemaining(field -> {
                    String columnKey = field.getKey();
                    String columnValue = field.getValue().asText();

                    for (ColumnModel column : renameColumns) {
                        columnKey = column.getOldColumn().equals(columnKey) ? column.getNewColumn() : columnKey;
                    }

                    updateItem.put(columnKey, columnValue);
                });

                updatedData.add(updateItem.toString());
            }
        }

        return mapper.readTree(updatedData.toString());
    }
}
