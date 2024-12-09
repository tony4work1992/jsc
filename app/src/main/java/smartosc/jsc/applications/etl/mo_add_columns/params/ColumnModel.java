package smartosc.jsc.applications.etl.mo_add_columns.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String column;
    private String value;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
