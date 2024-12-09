package smartosc.jsc.applications.etl.mo_transformer.executors.mo_union.params;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.IParamsModel;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UnionParamsModel implements IParamsModel {
    private int[] nodeIds;

    public UnionParamsModel(JsonNode configs) {
        if (!configs.has("nodeIds")) {
            throw new IllegalArgumentException("Column configs must contain an 'nodeIds' field");
        }
        String[] nodeIds = configs.get("nodeIds").asText().split(",");
        this.nodeIds = Arrays.stream(nodeIds).mapToInt(Integer::parseInt).toArray();
    }
}