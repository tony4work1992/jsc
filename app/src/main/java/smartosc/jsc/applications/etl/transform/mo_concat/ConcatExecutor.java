package smartosc.jsc.applications.etl.transform.mo_concat;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import smartosc.jsc.applications.etl.transform.ba_nodes.Executable;
import smartosc.jsc.applications.etl.transform.mo_concat.params.ConcatModel;
import smartosc.jsc.applications.etl.transform.mo_concat.params.ConcatParamsExtractor;

public class ConcatExecutor implements Executable {

    @Override
    public JsonNode execute(String params, JsonNode data) throws Exception {

        ConcatParamsExtractor extractor = new ConcatParamsExtractor();
        List<ConcatModel> convertedParams = extractor.extractParams(params);

        JsonNode returnData = data.deepCopy();
        if (!returnData.isArray()) {
            throw new RuntimeException("[ConcatExecutor] Data is not an array");
        }
        // TODO: refactor this
        for (ConcatModel column : convertedParams) {
            if (column.getConcatColumns().size() < 2) {
                throw new RuntimeException("[ConcatExecutor] Concat columns size are less than 2");
            }
            for (JsonNode item : returnData) {
                if (!item.isObject()) {
                    throw new RuntimeException("[ConcatExecutor] Data item is not an object");
                }
                StringBuilder concatValue = new StringBuilder();

                for (int i = 0; i < column.getConcatColumns().size(); i++) {
                    String col = column.getConcatColumns().get(i); // Access element by index

                    if (!item.has(col)) {
                        throw new RuntimeException("[ConcatExecutor] Column not found in data");
                    }

                    if (i == 0) {
                        concatValue.append(item.get(col).asText());
                    } else {
                        concatValue.append("_").append(item.get(col).asText());
                    }
                }
                ((ObjectNode) item).put(column.getNewColumn(), concatValue.toString());

            }
        }
        return returnData;
    }
}
