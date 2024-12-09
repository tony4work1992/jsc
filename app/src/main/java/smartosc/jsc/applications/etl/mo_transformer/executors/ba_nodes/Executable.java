package smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes;

import com.fasterxml.jackson.databind.JsonNode;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_filter.params.FilterParamsModel;

import java.util.List;
import java.util.Map;

public interface Executable<T> {
    public Map<Number, JsonNode> execute(T params, Map<Number, JsonNode> datasetByNodes, int id, int parentId) throws Exception;
}
