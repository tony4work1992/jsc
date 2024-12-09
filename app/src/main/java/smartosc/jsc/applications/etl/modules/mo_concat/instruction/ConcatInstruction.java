package smartosc.jsc.applications.etl.modules.mo_concat.instruction;

import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;

public class ConcatInstruction extends AbstractInstruction{
    public static final String NEW_COL_NAME = "new_col_name";
    public static final String CONCAT_COLS_NAME = "concat_cols_name";

    private String newColName;
    private String[] concatColNames;

    public ConcatInstruction(String newColName, String[] concatColNames) {
        this.newColName = newColName;
        this.concatColNames = concatColNames;
    }

    // Getters and setters
    public String getNewColName() {
        return this.newColName;
    }

    public void setNewColName(String newColName) {
        this.newColName = newColName;
    }

    public String[] getConcatColNames() {
        return this.concatColNames;
    }

    public void setConcatColNames(String[] concatColNames) {
        this.concatColNames = concatColNames;
    }
    
}
