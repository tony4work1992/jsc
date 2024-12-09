package smartosc.jsc.applications.etl.module.mo_add_columns.params;

import java.util.List;

import lombok.Data;

@Data
public class ParamsModel {
    private Integer id;
    private List<Integer> children;
    private List<Integer> parent;
    private List<ColumnModel> config;
}
