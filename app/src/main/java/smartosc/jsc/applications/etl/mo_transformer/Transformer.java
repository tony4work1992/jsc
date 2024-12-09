package smartosc.jsc.applications.etl.mo_transformer;

import com.fasterxml.jackson.databind.JsonNode;
import smartosc.jsc.applications.etl.mo_commanders.TransformerCommand;

import java.util.*;

public class Transformer {
    public JsonNode transform(JsonNode data, List<TransformerCommand> commandsQueue) throws Exception {
        Map<Number, JsonNode> datasetByNodes = new HashMap<>();
        datasetByNodes.put(0, data.deepCopy());
        for(TransformerCommand transformerCommand : commandsQueue) {
           try {
               datasetByNodes = transformerCommand.getTransformer().execute(transformerCommand.getParams(), datasetByNodes, transformerCommand.getId(), transformerCommand.getParentId());
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
        }

        String max = datasetByNodes.keySet().stream()
                .max((a, b) -> a.intValue() > b.intValue() ? 1 : -1).get().toString();
        System.out.println(max);
        return datasetByNodes.get(Integer.parseInt(max));
    }
}
