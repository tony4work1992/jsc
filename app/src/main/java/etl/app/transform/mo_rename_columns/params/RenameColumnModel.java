package etl.app.transform.mo_rename_columns.params;

import lombok.Data;

@Data
public class RenameColumnModel {

    private String oldColumn;
    private String newColumn;
}
