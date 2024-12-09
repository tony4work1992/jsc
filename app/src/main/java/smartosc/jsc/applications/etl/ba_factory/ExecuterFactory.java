package smartosc.jsc.applications.etl.ba_factory;

import smartosc.jsc.applications.etl.mo_load_dataset.LoadDatasetExecuter;
import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExecuter;
import smartosc.jsc.applications.etl.mo_remove_columns.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.mo_concat_columns.ConcatColumnsExcuter;
import smartosc.jsc.applications.etl.mo_filter_values.FiltterValuesExecuter;
import smartosc.jsc.applications.etl.mo_union_nodes.UnionNodesExecuter;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;

public class ExecuterFactory {

    public static Executable getExecutable(String name, Map<Integer, JsonNode> nodeResults) {
        return switch (name) {
            case ExecuterType.LOAD_DATASET -> new LoadDatasetExecuter();
            case ExecuterType.ADD_COLUMNS -> new AddColumnsExecuter();
            case ExecuterType.RENAME_COLUMNS -> new RenameColumnsExecuter();
            case ExecuterType.CONCATE_COLUMNS -> new ConcatColumnsExcuter();
            case ExecuterType.REMOVE_COLUMNS -> new RemoveColumnsExecuter();
            case ExecuterType.FILTER_VALUES -> new FiltterValuesExecuter();
            case ExecuterType.UNION_NODES -> new UnionNodesExecuter(nodeResults);
            default -> throw new IllegalArgumentException("Unknown module: " + name);
        };
    }
}
