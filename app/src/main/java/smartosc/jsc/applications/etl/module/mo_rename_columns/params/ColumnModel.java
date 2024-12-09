package smartosc.jsc.applications.etl.module.mo_rename_columns.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String columnToRename;
    private String columnRenameTo;
}
