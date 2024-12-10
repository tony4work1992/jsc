package smartosc.jsc.applications.etl.mo_filter_values;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_filter_values.params.FilterValuesParamsExtractor;
import smartosc.jsc.applications.etl.mo_filter_values.params.ColumnModel;

public class FilterValuesExecuter implements Executable {
    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        FilterValuesParamsExtractor extractor = new FilterValuesParamsExtractor();
        List<ColumnModel> filterValues = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
                ObjectMapper objectMapper = new ObjectMapper();

        // Tạo mảng mới để lưu các đối tượng đã lọc
        ArrayNode filteredArrayNode = objectMapper.createArrayNode();

        // Lọc các đối tượng thỏa mãn điều kiện: age > 30 và (name chứa "Ngoc" hoặc city chứa "Ha")
        for (ColumnModel filterValue : filterValues) {
            for (JsonNode node : returnData) {
                if (this.checkCondition(node, filterValue.getCondition(), filterValue.getColumn(), filterValue.getValue())) {
                    // Thêm đối tượng vào mảng đã lọc
                    filteredArrayNode.add(node);
                }
            }
        }

        return objectMapper.readTree(filteredArrayNode.toString());
    }

    private Boolean checkCondition(JsonNode node, String condition, String column, String filterValue) {
        JsonNode value = node.get(column);
        String currentValue = value.asText();
        if (node.has(column)) {
            switch (condition) {
                case "=":
                    return currentValue.equals(filterValue);
                case "like":
                    return currentValue.contains(filterValue);
                case ">":
                    return currentValue.compareTo(filterValue) > 0;
            }
        }

        return false;
    }
}
