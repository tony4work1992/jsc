package smartosc.jsc.applications.etl.modules.mo_filter.instruction;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;

public class FilterInstruction extends AbstractInstruction {
    private String columnName;
    private String operation;
    private String value;

    public static final String COL_NAME = "col_name";

    public static final String OPERATION = "operation";

    public static final String VALUE = "value";

    public FilterInstruction(String columnName, String operation, String value) {
        this.columnName = columnName;
        this.operation = operation;
        this.value = value;
    }

    // Getters and setters
    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}