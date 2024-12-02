package smartosc.jsc.applications.etl.mo_concat_columns.params;

import lombok.Data;

import java.lang.reflect.Array;

@Data
public class ColumnModel {
    private String concatColumnName;
    private String[] concatColumns;
}
