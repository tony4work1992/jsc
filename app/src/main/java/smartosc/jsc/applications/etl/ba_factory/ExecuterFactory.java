package smartosc.jsc.applications.etl.ba_factory;

import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExecuter;
import smartosc.jsc.applications.etl.mo_remove_columns.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.mo_concat_columns.ConcatColumnsExcuter;
import smartosc.jsc.applications.etl.mo_filter_values.FiltterValuesExecuter;
import smartosc.jsc.applications.etl.ba_nodes.Executable;

public class ExecuterFactory {

    public static Executable getExecuter(String type) {
        switch (type) {
            case ExecuterType.ADD_COLUMNS:
                return new AddColumnsExecuter();
            case ExecuterType.RENAME_COLUMNS:
                return new RenameColumnsExecuter();
            case ExecuterType.CONCATE_COLUMNS:
                return new ConcatColumnsExcuter();
            case ExecuterType.REMOVE_COLUMNS:
                return new RemoveColumnsExecuter();
            case ExecuterType.FILTER_VALUES:
                return new FiltterValuesExecuter();
            default:
                throw new IllegalArgumentException("Unknown executer type: " + type);
        }
    }
}
