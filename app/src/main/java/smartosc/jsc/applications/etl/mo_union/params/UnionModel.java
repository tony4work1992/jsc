package smartosc.jsc.applications.etl.mo_union.params;

import lombok.Data;

@Data
public class UnionModel {
    private String filterColumn;
    private String operator;
    private String filterValue;
}
