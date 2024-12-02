package smartosc.jsc.applications.etl.mo_filter_columns.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String column;
    private String condition;
    private String value;
}
