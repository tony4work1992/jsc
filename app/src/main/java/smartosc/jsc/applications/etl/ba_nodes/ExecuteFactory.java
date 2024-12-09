package smartosc.jsc.applications.etl.ba_nodes;

import com.fasterxml.jackson.databind.JsonNode;
import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecute;
import smartosc.jsc.applications.etl.mo_concat_columns.ConcatColumnsExecute;
import smartosc.jsc.applications.etl.mo_filter_columns.FilterColumnsExecute;
import smartosc.jsc.applications.etl.mo_load_data.LoadDataExecute;
import smartosc.jsc.applications.etl.mo_remove_columns.RemoveColumnsExecute;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExecute;
import smartosc.jsc.applications.etl.mo_union_columns.UnionColumnsExecute;

import java.util.Map;

public class ExecuteFactory {
    public Executable create(String executeName, Map<Integer, JsonNode> result) {
        return switch (executeName) {
            case "LOAD_COLUMNS" -> new LoadDataExecute();
            case "ADD_COLUMNS" -> new AddColumnsExecute();
            case "CONCAT_COLUMNS" -> new ConcatColumnsExecute();
            case "FILTER_COLUMNS" -> new FilterColumnsExecute();
            case "REMOVE_COLUMNS" -> new RemoveColumnsExecute();
            case "RENAME_COLUMNS" -> new RenameColumnsExecute();
            case "UNION_COLUMNS" -> new UnionColumnsExecute(result);
            default -> null;
        };
    }
}
