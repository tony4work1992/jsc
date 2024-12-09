package smartosc.jsc.applications.etl.mo_concat_columns.params;

import lombok.Data;

import java.lang.reflect.Array;

@Data
public class ColumnModel {
    private String concatColumnName;
    private String[] concatColumns;

    public String getConcatColumnName() {
        return concatColumnName;
    }

    public void setConcatColumnName(String concatColumnName) {
        this.concatColumnName = concatColumnName;
    }

    public String[] getConcatColumns() {
        return concatColumns;
    }

    public void setConcatColumns(String[] concatColumns) {
        this.concatColumns = concatColumns;
    }
}
