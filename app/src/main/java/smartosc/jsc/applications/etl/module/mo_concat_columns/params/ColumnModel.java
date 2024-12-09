package smartosc.jsc.applications.etl.module.mo_concat_columns.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String newColumnName;
    private String[] columns;
}
