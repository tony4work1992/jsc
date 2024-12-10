package smartosc.jsc.applications.etl.constant;

public final class Constants {

    private Constants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }

    public static final String OPERATOR_EQUALS = "=";
    public static final String OPERATOR_GREATER_THAN = ">";
    public static final String OPERATOR_LESS_THAN = "<";
    public static final String OPERATOR_CONTAINS = "contains";
    public static final String OPERATOR_NOT_CONTAINS = "not_contains";

    public static final String ADD_COLUMNS = "addColumns";
    public static final String FILTER_COLUMNS = "filterColumns";
    public static final String CONCAT_COLUMNS = "concatColumns";
    public static final String RENAME_COLUMNS = "renameColumns";
    public static final String REMOVE_COLUMNS = "removeColumns";
    public static final String UNION_COLUMNS = "unionColumns";
    public static final String LOAD_DATA = "loadData";
}
