package smartosc.jsc.applications.etl.modules.mo_tree_builder;

import smartosc.jsc.applications.etl.modules.base.DataSet;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InstructionGraph {
    private AbstractInstruction root;

    private List<AbstractInstruction> instructionNodes;

    private Map<Integer, AbstractInstruction> instructionsMap;

    public InstructionGraph() {

    }

    public AbstractInstruction getRoot() {
        return root;
    }

    public void setRoot(AbstractInstruction root) {
        this.root = root;
    }

    public List<AbstractInstruction> getInstructionNodes() {
        return instructionNodes;
    }

    public void setInstructionNodes(List<AbstractInstruction> instructionNodes) {
        this.instructionNodes = instructionNodes;
    }

    public Map<Integer, AbstractInstruction> getInstructionsMap() {
        return instructionsMap;
    }

    public void setInstructionsMap(Map<Integer, AbstractInstruction> instructionsMap) {
        this.instructionsMap = instructionsMap;
    }
}
