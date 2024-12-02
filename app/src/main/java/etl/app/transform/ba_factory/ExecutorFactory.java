package etl.app.transform.ba_factory;

import etl.app.transform.ba_nodes.Executable;
import etl.app.transform.contants.EExecutor;
import etl.app.transform.mo_add_columns.AddColumnsExecutor;
import etl.app.transform.mo_concat.ConcatExecutor;
import etl.app.transform.mo_filter.FilterExecutor;
import etl.app.transform.mo_remove_columns.RemoveColumnsExecutor;
import etl.app.transform.mo_rename_columns.RenameColumnsExecutor;

public class ExecutorFactory {

    public static Executable createExecutor(EExecutor executorName) throws IllegalArgumentException {
        switch (executorName) {
            case ADD_COLUMNS:
                return new AddColumnsExecutor();
            case CONCAT:
                return new ConcatExecutor();
            case FILTER:
                return new FilterExecutor();
            case REMOVE_COLUMNS:
                return new RemoveColumnsExecutor();
            case RENAME_COLUMNS:
                return new RenameColumnsExecutor();
            default:
                throw new IllegalArgumentException("Invalid executor name: " + executorName);
        }
    }
}
