package smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes;

import smartosc.jsc.applications.etl.constants.TransformerType;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_concat.ConcatExecutor;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_add_column.AddColumnsExecutor;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_filter.FilterExecutor;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_remove_column.RemoveColumnExecutor;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_rename_column.RenameColumnExecutor;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_union.UnionExecutor;

public class TransformerFactory {
    public Executable create(TransformerType type) {
        switch (type) {
            case FILTER:
                return new FilterExecutor();
            case CONCAT:
                return new ConcatExecutor();
            case ADD_COLUMNS:
                return new AddColumnsExecutor();
            case REMOVE_COLUMNS:
                return new RemoveColumnExecutor();
            case RENAME_COLUMNS:
                return new RenameColumnExecutor();
            case UNION:
                return new UnionExecutor();
            default:
                throw new IllegalArgumentException("Unknown transformer type: " + type);
        }
    }
}
