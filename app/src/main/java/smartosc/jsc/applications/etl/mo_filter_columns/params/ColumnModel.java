package smartosc.jsc.applications.etl.mo_filter_columns.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String column;
    private String condition;
    private String value;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
