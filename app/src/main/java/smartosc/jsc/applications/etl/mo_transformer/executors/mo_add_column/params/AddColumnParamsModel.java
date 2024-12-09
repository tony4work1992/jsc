package smartosc.jsc.applications.etl.mo_transformer.executors.mo_add_column.params;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.IParamsModel;

@Data
public class AddColumnParamsModel implements IParamsModel {
    private String column;
    private String value;

    public AddColumnParamsModel(JsonNode config) {
        if (!config.has("column") || !config.get("column").isTextual()) {
            throw new IllegalArgumentException("Column config is missing or incorrect");
        }
        this.column = config.get("column").asText();
        this.value = config.get("value").asText(null); //todo: check config not have value field
    }
}
