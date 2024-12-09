package smartosc.jsc.applications.etl.mo_nodes.rename.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String oldColumn;
    private String newColumn;
}
