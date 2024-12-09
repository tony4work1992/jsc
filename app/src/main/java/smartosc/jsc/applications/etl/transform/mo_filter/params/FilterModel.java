package smartosc.jsc.applications.etl.transform.mo_filter.params;

import lombok.Data;

@Data
public class FilterModel {

    private String filterColumn;
    private String operator;
    private String filterValue;
}
