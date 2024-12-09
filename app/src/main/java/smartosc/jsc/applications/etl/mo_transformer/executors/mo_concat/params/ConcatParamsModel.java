package smartosc.jsc.applications.etl.mo_transformer.executors.mo_concat.params;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.IParamsModel;

@Data
public class ConcatParamsModel implements IParamsModel {
    private String newColumnName;
    private String[] concatColumns;
    private String delimiter;

    // Constructor with varargs
    public ConcatParamsModel(JsonNode configs) {
        if (!configs.has("newColumnName") || !configs.has("concatColumns") || !configs.has("delimiter")) {
            throw new IllegalArgumentException("New column name and concat columns must be provided");
        }
        this.newColumnName = configs.get("newColumnName").asText();
        this.concatColumns = configs.get("concatColumns").asText().split(",");
        this.delimiter = configs.get("delimiter").asText();
    }
}