package smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes;

import com.fasterxml.jackson.databind.JsonNode;
import smartosc.jsc.applications.etl.constants.TransformerType;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_add_column.params.AddColumnParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_concat.params.ConcatParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_filter.params.FilterParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_remove_column.params.RemoveColumnParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_rename_column.params.RenameColumnParamsModel;
import smartosc.jsc.applications.etl.mo_transformer.executors.mo_union.params.UnionParamsModel;

public class ColumnModelFactory {
    public IParamsModel createColumnModel(TransformerType type, JsonNode columnConfigs) {
        if (columnConfigs == null) {
            throw new IllegalArgumentException("columnConfigs is null");
        }
        switch (type) {
            case ADD_COLUMNS:
                return new AddColumnParamsModel(columnConfigs);
            case FILTER:
                return new FilterParamsModel(columnConfigs);
            case CONCAT:
                return new ConcatParamsModel(columnConfigs);
            case REMOVE_COLUMNS:
                return new RemoveColumnParamsModel(columnConfigs);
            case RENAME_COLUMNS:
                return new RenameColumnParamsModel(columnConfigs);
            case UNION:
                return new UnionParamsModel(columnConfigs);
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}
