package smartosc.jsc.applications.etl.mo_transformer.executors.mo_filter.params;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import smartosc.jsc.applications.etl.constants.FilterOperator;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.IParamsModel;

@Data
public class FilterParamsModel implements IParamsModel {
    private String column;
    private String value;
    private FilterOperator operator;

    public FilterParamsModel(JsonNode configs) {
        this.column = configs.get("column").asText();
        this.value = configs.get("value").asText(); //check if value is number
        this.operator = FilterOperator.valueOf(configs.get("operator").asText());
    }
}