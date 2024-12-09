package smartosc.jsc.applications.etl.mo_nodes.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import smartosc.jsc.applications.etl.ba_nodes.Node;
import smartosc.jsc.applications.etl.mo_nodes.filter.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_nodes.filter.constants.Oprators;
import smartosc.jsc.applications.etl.mo_nodes.filter.params.FilterParamExtractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterNode implements Node {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<String> updatedData = new ArrayList<>();
        FilterParamExtractor extractor = new FilterParamExtractor();
        List<ColumnModel> filterParams = extractor.extractParams(params);
        JsonNode dataset = data.deepCopy();

        if (!dataset.isArray()) {
            throw new RuntimeException("Data is not an array");
        }

        for (JsonNode item : dataset) {
            for (JsonNode row : item) {
                boolean matches = false;

                for (ColumnModel filterParam : filterParams) {
                    String columnValue = row.get(filterParam.getColumn()).asText();

                    switch (filterParam.getOperator()) {
                        case Oprators.EQUALS:
                            matches = columnValue.contains(filterParam.getValue());
                            break;
                        case Oprators.NOT_EQUALS:
                            matches = !columnValue.contains(filterParam.getValue());
                            break;
                        case Oprators.GREATER_THAN:
                            try {
                                matches = Integer.parseInt(columnValue) > Integer.parseInt(filterParam.getValue());
                                break;
                            } catch (NumberFormatException e) {
                                throw new RuntimeException("Column value " + columnValue + " is not a number");
                            }
                        case Oprators.LESS_THAN:
                            try {
                                matches = Integer.parseInt(columnValue) < Integer.parseInt(filterParam.getValue());
                                break;
                            } catch (NumberFormatException e) {
                                throw new RuntimeException("Column value " + columnValue + " is not a number");
                            }
                        default:
                            throw new RuntimeException("Operator " + filterParam.getOperator() + " is not supported");
                    }
                }

                if (matches) {
                    updatedData.add(row.toString());
                }
            }
        }

        return mapper.readTree(updatedData.toString());
    }
}
