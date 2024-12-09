package smartosc.jsc.applications.etl.modules.mo_add.instruction;

import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;

public class AddInstruction extends AbstractInstruction{
    private String colName;
    private String defaultValue;

    public AddInstruction(String colName, String defaultValue) {
        this.colName = colName;
        this.defaultValue = defaultValue;
    }

    // Getters and setters
    public String getColName() {
        return this.colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
