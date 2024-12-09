package smartosc.jsc.applications.etl.mo_transformer.executors.mo_remove_column.params;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.IParamsModel;

@Data
public class RemoveColumnParamsModel implements IParamsModel {
    private String column;

    public RemoveColumnParamsModel(JsonNode configs) {
        if (!configs.has("column")) {
            throw new IllegalArgumentException("Column configs must contain an 'column' field");
        }
        this.column = configs.get("column").asText();
    }
}