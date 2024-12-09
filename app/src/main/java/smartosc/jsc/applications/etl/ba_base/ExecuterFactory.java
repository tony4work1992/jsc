package smartosc.jsc.applications.etl.ba_base;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.mo_concat_columns.ConcatColumnsExecuter;
import smartosc.jsc.applications.etl.mo_filter_columns.FilterColumnsExecuter;
import smartosc.jsc.applications.etl.mo_load_dataset.LoadDatasetExecuter;
import smartosc.jsc.applications.etl.mo_remove_colums.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExecuter;
import smartosc.jsc.applications.etl.mo_union_datasets.UnionDatasetsExecuter;

public class ExecuterFactory {
    public static final String LOAD_DATASET = "LoadData";
    public static final String ADD_COLUMNS = "AddColumns";
    public static final String RENAME_COLUMNS = "RenameColumns";
    public static final String CONCAT_COLUMNS = "Concat";
    public static final String REMOVE_COLUMNS = "Remove";
    public static final String FILTER_VALUES = "Filter";
    public static final String UNION_DATASET = "Union";

    public static Executable getExecuter(String type) {
        return switch (type) {
            case LOAD_DATASET -> new LoadDatasetExecuter();
            case ADD_COLUMNS -> new AddColumnsExecuter();
            case RENAME_COLUMNS -> new RenameColumnsExecuter();
            case CONCAT_COLUMNS -> new ConcatColumnsExecuter();
            case REMOVE_COLUMNS -> new RemoveColumnsExecuter();
            case FILTER_VALUES -> new FilterColumnsExecuter();
            case UNION_DATASET -> new UnionDatasetsExecuter();
            default -> throw new IllegalArgumentException("Undefined execute type: " + type);
        };
    }

}
