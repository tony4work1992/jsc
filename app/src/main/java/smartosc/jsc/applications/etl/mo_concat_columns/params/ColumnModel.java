package smartosc.jsc.applications.etl.mo_concat_columns.params;

import java.util.List;

import lombok.Data;

@Data
public class ColumnModel {
    private String newColumnName;
    private List<String> sourceColumns;

    public ColumnModel(String newColumnName, List<String> sourceColumns) {
        this.newColumnName = newColumnName;
        this.sourceColumns = sourceColumns;
    }

    public String getNewColumnName() {
        return newColumnName;
    }

    public void setNewColumnName(String newColumnName) {
        this.newColumnName = newColumnName;
    }

    public List<String> getSourceColumns() {
        return sourceColumns;
    }

    public void setSourceColumns(List<String> sourceColumns) {
        this.sourceColumns = sourceColumns;
    }
}
