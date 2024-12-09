package smartosc.jsc.applications.etl.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransformerType {
    FILTER("filter"),
    CONCAT("concat"),
    ADD_COLUMNS("add_columns"),
    REMOVE_COLUMNS("remove_columns"),
    RENAME_COLUMNS("rename_columns"),
    UNION("union"),;
    
    private final String type;

}
