package smartosc.jsc.applications.etl.mo_commanders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.constants.TransformerType;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.ColumnModelFactory;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.TransformerFactory;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.IParamsModel;

import java.util.ArrayList;
import java.util.List;

public class CommanderCreation {
    private final ColumnModelFactory columnModelFactory;
    private final TransformerFactory transformerFactory;

    public CommanderCreation() {
        this.columnModelFactory = new ColumnModelFactory();
        this.transformerFactory = new TransformerFactory();
    }
    public List<TransformerCommand> buildCommandQueue(String stringConfigs) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode configs = mapper.readTree(stringConfigs).get("nodes");
        //Sort configs by id
        List<TransformerCommand> commandQueue = new ArrayList<>();
        configs.forEach(jsonNode -> {
            String actionName = jsonNode.get("name").asText();
            int id = jsonNode.get("id").asInt();
            int parentId = jsonNode.has("parent") && !jsonNode.get("parent").isNull()
                    ? jsonNode.get("parent").asInt()
                    : -1;
            List<IParamsModel> columnModels = this.getColumnModels(actionName, jsonNode.get("config"));

            Executable transformer = this.transformerFactory.create(TransformerType.valueOf(actionName));

            commandQueue.add(new TransformerCommand(transformer, columnModels, id, parentId));
        });

        return commandQueue;
    }

    private List<IParamsModel> getColumnModels(String actionName, JsonNode columnConfig) {
        List<IParamsModel> columnModels = new ArrayList<>();

        columnConfig.forEach(config -> {
            IParamsModel columnModel = columnModelFactory.createColumnModel(TransformerType.valueOf(actionName), config);
            columnModels.add(columnModel);
        });

        return columnModels;
    }
}
