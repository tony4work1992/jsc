package smartosc.jsc.applications.etl.mo_filter_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_filter_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_filter_columns.params.FilterColumnsParamsExtractor;

public class FiltterColumnsExecutor implements Executable {
    public JsonNode execute(String params, JsonNode dataset) throws Exception {
        FilterColumnsParamsExtractor filterColumnsParamExtractor = new FilterColumnsParamsExtractor();
        List<ColumnModel> filterConditions = filterColumnsParamExtractor.extractParams(params);
        ObjectMapper objectMapper = new ObjectMapper();
        // Validate input data
        if (!dataset.isArray()) {
            throw new RuntimeException("Data set is invalid");
        }

        ArrayNode dataReturn = (ArrayNode)dataset.deepCopy();

        return  dataReturn;
    }

}
