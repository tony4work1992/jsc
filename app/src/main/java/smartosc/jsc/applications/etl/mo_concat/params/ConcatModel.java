package smartosc.jsc.applications.etl.mo_concat.params;

import java.util.List;

import lombok.Data;

@Data
public class ConcatModel {

    private String newColumn;
    private List<String> concatColumns;
}
