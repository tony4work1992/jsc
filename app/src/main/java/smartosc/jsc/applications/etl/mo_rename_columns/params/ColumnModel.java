package smartosc.jsc.applications.etl.mo_rename_columns.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String oldColumnName;
    private String newColumnName;

    public String getOldColumnName() {
        return oldColumnName;
    }

    public void setOldColumnName(String oldColumnName) {
        this.oldColumnName = oldColumnName;
    }

    public String getNewColumnName() {
        return newColumnName;
    }

    public void setNewColumnName(String newColumnName) {
        this.newColumnName = newColumnName;
    }
}
