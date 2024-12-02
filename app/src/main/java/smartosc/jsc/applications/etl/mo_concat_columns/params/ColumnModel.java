package smartosc.jsc.applications.etl.mo_concat_columns.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String newColumn;
    private String[] currentColumns;

}
