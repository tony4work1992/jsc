package smartosc.jsc.applications.etl.modules.mo_union.instruction;

import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;

import java.util.ArrayList;

public class UnionInstruction extends AbstractInstruction{

    private ArrayList<String> parentIds;

    public static final String UNION_IDS = "union_ids";

    public UnionInstruction(ArrayList<String> parentIds) {
        this.parentIds = parentIds;
    }

    public ArrayList<String> getParentIds() {
        return parentIds;
    }
}
