package smartosc.jsc.applications.etl.ba_factory;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.constants.AppConstants;
import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.mo_concat_value.ConcatValueExecuter;
import smartosc.jsc.applications.etl.mo_filter_columns.FilterColumnsExecuter;
import smartosc.jsc.applications.etl.mo_load_data.LoadDataExecuter;
import smartosc.jsc.applications.etl.mo_remove_columns.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExecuter;
import smartosc.jsc.applications.etl.mo_union.UnionExecuter;

public class ExecuterFactory {
    public Executable getExecuter(String type) {
        switch (type) {
            case AppConstants.ADD_COLUMN_EXECUTER -> {
                return new AddColumnsExecuter();
            }

            case AppConstants.CONCAT_COLUMN_EXECUTER -> {
                return new ConcatValueExecuter();
            }

            case AppConstants.FILTER_COLUMN_EXECUTER -> {
                return new FilterColumnsExecuter();
            }

            case AppConstants.RENAME_COLUMN_EXECUTER -> {
                return new RenameColumnsExecuter();
            }

            case AppConstants.REMOVE_COLUMN_EXECUTER -> {
                return new RemoveColumnsExecuter();
            }

            case AppConstants.LOAD_DATA_EXECUTER -> {
                return new LoadDataExecuter();
            }

            case AppConstants.UNION_EXECUTER -> {
                return new UnionExecuter();
            }

            default -> {
                throw new RuntimeException("Exercuter " + type + " does not exists");
            }

        }
    }
}