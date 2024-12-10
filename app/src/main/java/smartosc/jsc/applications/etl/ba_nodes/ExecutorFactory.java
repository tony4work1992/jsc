package smartosc.jsc.applications.etl.ba_nodes;

import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.constants.AppConstants;
import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.mo_concat_values.ConcatValuesExecuter;
import smartosc.jsc.applications.etl.mo_filter_values.FilterValuesExecuter;
import smartosc.jsc.applications.etl.mo_remove_columns.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExecuter;
import smartosc.jsc.applications.etl.mo_load_data.LoadDataExecuter;
import smartosc.jsc.applications.etl.mo_load_data.LoadDataExecuter;
import smartosc.jsc.applications.etl.mo_union.UnionExecuter;

public class ExecutorFactory {
    public static Executable getExecutor(String type, Map<Integer, JsonNode> nodeResults) {
        switch (type) {
            case AppConstants.FILTER_VALUES_EXECUTER:
                return new FilterValuesExecuter();
            case AppConstants.RENAME_COLUMNS_EXECUTER:
                return new RenameColumnsExecuter();
            case AppConstants.ADD_COLUMNS_EXECUTER:
                return new AddColumnsExecuter();
            case AppConstants.REMOVE_COLUMN_EXECUTER:
                return new RemoveColumnsExecuter();
            case AppConstants.CONCAT_VALUES_EXECUTER:
                return new ConcatValuesExecuter();
            case AppConstants.LOAD_DATA_EXCUTER:
                return new LoadDataExecuter();
            case AppConstants.UNION_EXECUTER:
                return new UnionExecuter(nodeResults);
            default:
                throw new IllegalArgumentException("Invalid executor type: " + type);
        }
    }
}
