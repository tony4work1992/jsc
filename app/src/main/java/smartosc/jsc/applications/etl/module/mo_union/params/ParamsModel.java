package smartosc.jsc.applications.etl.module.mo_union.params;

import java.util.List;

import lombok.Data;

@Data
public class ParamsModel {
    private Integer id;
    private List<Integer> children;
    private List<Integer> parent;
    private String config;
}
