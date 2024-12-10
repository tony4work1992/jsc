package smartosc.jsc.applications.etl.mo_rename_columns.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String currentColumnName;
    private String newColumnName;

    public ColumnModel(String currentColumnName, String newColumnName) {
        this.currentColumnName = currentColumnName;
        this.newColumnName = newColumnName;
    }

    public String getCurrentColumnName() {
        return currentColumnName;
    }

    public void setCurrentColumnName(String currentColumnName) {
        this.currentColumnName = currentColumnName;
    }

    public String getNewColumnName() {
        return newColumnName;
    }

    public void setNewColumnName(String newColumnName) {
        this.newColumnName = newColumnName;
    }
}
