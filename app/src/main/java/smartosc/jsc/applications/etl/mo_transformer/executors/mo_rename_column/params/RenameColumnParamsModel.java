package smartosc.jsc.applications.etl.mo_transformer.executors.mo_rename_column.params;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.IParamsModel;

@Data
@AllArgsConstructor
public class RenameColumnParamsModel implements IParamsModel {
    private String oldColumnName;
    private String newColumnName;

    public RenameColumnParamsModel(JsonNode configs) {
        if (!configs.has("newColumnName") || !configs.has("oldColumnName")) {
            throw new IllegalArgumentException("Column configs must contain an 'column' field");
        }
        this.oldColumnName = configs.get("oldColumnName").asText();
        this.newColumnName = configs.get("newColumnName").asText();
    }
}