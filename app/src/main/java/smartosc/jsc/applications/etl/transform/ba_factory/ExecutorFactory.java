package smartosc.jsc.applications.etl.transform.ba_factory;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import smartosc.jsc.applications.etl.contants.EExecutor;
import smartosc.jsc.applications.etl.transform.ba_nodes.Executable;
import smartosc.jsc.applications.etl.transform.mo_add_columns.AddColumnsExecutor;
import smartosc.jsc.applications.etl.transform.mo_concat.ConcatExecutor;
import smartosc.jsc.applications.etl.transform.mo_filter.FilterExecutor;
import smartosc.jsc.applications.etl.transform.mo_load_data.LoadDataExecutor;
import smartosc.jsc.applications.etl.transform.mo_remove_columns.RemoveColumnsExecutor;
import smartosc.jsc.applications.etl.transform.mo_rename_columns.RenameColumnsExecutor;
import smartosc.jsc.applications.etl.transform.mo_union.UnionExecutor;

public class ExecutorFactory {

    public static Executable createExecutor(EExecutor executorName, Map<String, JsonNode> listResult) throws IllegalArgumentException {
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
            case LOAD_DATA:
                return new LoadDataExecutor();
            case UNION:
                return new UnionExecutor(listResult);
            default:
                throw new IllegalArgumentException("Invalid executor name: " + executorName);
        }
    }
}
