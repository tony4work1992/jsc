package smartosc.jsc.applications.etl.module.mo_load_data.params;

import java.util.List;

import lombok.Data;

@Data
public class ParamsModel {
    private Integer id;
    private List<Integer> children;
    private String parent;
    private String config;
}
