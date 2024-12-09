package smartosc.jsc.applications.etl.mo_nodes.filter.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String column;
    private String operator;
    private String value;
}
