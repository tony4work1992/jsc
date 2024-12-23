package smartosc.jsc.applications.etl.ba_factory;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.constant.Constants;
import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.mo_concat_columns.ConcatColumnsExecuter;
import smartosc.jsc.applications.etl.mo_filter_columns.FilterColumnsExcuter;
import smartosc.jsc.applications.etl.mo_load_data.LoadDataExecuter;
import smartosc.jsc.applications.etl.mo_remove_columns.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExcuter;

public class ExecuterFactory {
    public static Executable getExecuter(String type) {
        return switch (type) {
            case Constants.ADD_COLUMNS -> new AddColumnsExecuter();
            case Constants.FILTER_COLUMNS -> new FilterColumnsExcuter();
            case Constants.CONCAT_COLUMNS -> new ConcatColumnsExecuter();
            case Constants.RENAME_COLUMNS -> new RenameColumnsExcuter();
            case Constants.REMOVE_COLUMNS -> new RemoveColumnsExecuter();
            case Constants.UNION_COLUMNS -> new ConcatColumnsExecuter();
            case Constants.LOAD_DATA -> new LoadDataExecuter();
            default -> throw new IllegalArgumentException("Invalid executer type: " + type);
        };
    }
}