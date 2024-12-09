package smartosc.jsc.applications.etl.modules.mo_remove.instruction;

import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;

public class RemoveInstruction extends AbstractInstruction{
    private String removeColName;

    public static final String COL_NAME = "col_name";

    public RemoveInstruction(String removeColName) {
        this.removeColName = removeColName;
    }

    // Getters and setters
    public String getRemoveColName() {
        return this.removeColName;
    }

    public void setRemoveColName(String removeColName) {
        this.removeColName = removeColName;
    }
}
