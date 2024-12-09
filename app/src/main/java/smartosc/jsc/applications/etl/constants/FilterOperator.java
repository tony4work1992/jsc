package smartosc.jsc.applications.etl.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilterOperator {
    EQ("="),
    IN("%"),
    GT(">"),
    GTEQ(">="),
    LT("<"),
    LTEQ("<=");

    private final String operator;

}