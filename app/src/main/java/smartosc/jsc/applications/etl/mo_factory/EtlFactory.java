package smartosc.jsc.applications.etl.mo_factory;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.mo_concat_columns.ConcatColumnsExecuter;
import smartosc.jsc.applications.etl.mo_filter_values.FilterValuesExecuter;
import smartosc.jsc.applications.etl.mo_load.LoadExecuter;
import smartosc.jsc.applications.etl.mo_remove_columns.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExecuter;
import smartosc.jsc.applications.etl.mo_union.UnionExecuter;

public class EtlFactory {

    public static final String LOAD_DATA = "load_data";
    public static final String ADD_COLUMNS = "add_columns";
    public static final String RENAME_COLUMNS = "rename_columns";
    public static final String FILTER = "filters";
    public static final String REMOVE_COLUMNS = "remove_columns";
    public static final String CONCAT_COLUMNS = "concat_columns";
    public static final String UNION = "union";

    public Executable getExecuter(String type, Map<Integer, JsonNode> result) {
        switch (type) {
            case ADD_COLUMNS -> {
                return new AddColumnsExecuter();
            }
            case RENAME_COLUMNS -> {
                return new RenameColumnsExecuter();
            }
            case FILTER -> {
                return new FilterValuesExecuter();
            }
            case REMOVE_COLUMNS -> {
                return new RemoveColumnsExecuter();
            }
            case CONCAT_COLUMNS -> {
                return new ConcatColumnsExecuter();
            }
            case LOAD_DATA -> {
                return new LoadExecuter();
            }
            case UNION -> {
                return new UnionExecuter(result);
            }

            default -> throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

}
