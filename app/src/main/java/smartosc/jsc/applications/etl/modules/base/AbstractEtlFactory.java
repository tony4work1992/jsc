package smartosc.jsc.applications.etl.modules.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;

public abstract class AbstractEtlFactory {

    public abstract CsvProcessorInterface createProcessor();

    public abstract AbstractInstruction createInstruction(Map<String, String> data);

    public AbstractInstruction prepareInstructionGraphData(JsonNode instructionNode, AbstractInstruction instruction) {
        String name = instructionNode.get("name").asText();
        int id = instructionNode.get("id").asInt();
        instruction.setId(id);
        instruction.setName(name);
        return instruction;
    }
}
