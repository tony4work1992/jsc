package smartosc.jsc.applications.etl.mo_filter_values.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String columnName;
    private String condition;
    private String value;
}
