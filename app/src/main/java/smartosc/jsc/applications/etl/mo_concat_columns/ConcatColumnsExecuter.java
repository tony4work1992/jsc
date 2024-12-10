package smartosc.jsc.applications.etl.mo_concat_columns;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ColumnModel;
import smartosc.jsc.applications.etl.mo_concat_columns.params.ConcatColumnsParamsExtractor;

public class ConcatColumnsExecuter implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {
        ConcatColumnsParamsExtractor extractor = new ConcatColumnsParamsExtractor();
        List<ColumnModel> concatColumns = extractor.extractParams(params);

        ArrayNode returnData = (ArrayNode) data.deepCopy();

        if (!returnData.isArray()) {
            throw new RuntimeException("Data is not an array");
        }
        for (JsonNode item : returnData) {
            if (!item.isObject()) {
                throw new RuntimeException("Data item is not an object");
            }

            ObjectNode objectItem = (ObjectNode) item;

            for (ColumnModel columnModel : concatColumns) {
                String newColumnName = columnModel.getNewColumnName();
                List<String> sourceColumns = columnModel.getSourceColumns();

                StringBuilder concatenatedValue = new StringBuilder();
                for (String sourceColumn : sourceColumns) {
                    if (objectItem.has(sourceColumn) && !objectItem.get(sourceColumn).isNull()) {
                        concatenatedValue.append(" ");
                        concatenatedValue.append(objectItem.get(sourceColumn).asText());
                    }
                }

                objectItem.put(newColumnName, concatenatedValue.toString());
            }
        }
        return returnData;
    }
}
