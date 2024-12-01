package smartosc.jsc.applications.etl.mo_rename_columns.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String oldColumnName;
    private String newColumnName;
}

