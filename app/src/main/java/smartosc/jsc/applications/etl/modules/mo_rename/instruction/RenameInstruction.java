package smartosc.jsc.applications.etl.modules.mo_rename.instruction;

import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;

public class RenameInstruction extends AbstractInstruction{
    private String oldColName;
    private String newColName;

    public static final String OLD_COL_NAME = "old_col_name";
    public static final String NEW_COL_NAME = "new_col_name";

    public RenameInstruction(String oldColName, String newColName) {
        this.oldColName = oldColName;
        this.newColName = newColName;
    }

    // Getters and setters
    public String getOldColName() {
        return this.oldColName;
    }

    public void setOldColName(String oldColName) {
        this.oldColName = oldColName;
    }

    public String getNewColName() {
        return this.newColName;
    }

    public void setNewColName(String newColName) {
        this.newColName = newColName;
    }
}
