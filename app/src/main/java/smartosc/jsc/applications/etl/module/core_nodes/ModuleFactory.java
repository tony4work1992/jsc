package smartosc.jsc.applications.etl.module.core_nodes;

import java.util.InputMismatchException;

import smartosc.jsc.applications.etl.module.ba_nodes.Executable;
import smartosc.jsc.applications.etl.module.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.module.mo_concat_columns.ConcatColumnsExecuter;
import smartosc.jsc.applications.etl.module.mo_filter_columns.FilterColumnsExecuter;
import smartosc.jsc.applications.etl.module.mo_remove_columns.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.module.mo_rename_columns.RenameColumnsExecuter;
import smartosc.jsc.applications.etl.module.mo_load_data.LoadDataExecuter;
import smartosc.jsc.applications.etl.module.mo_union.UnionExecuter;

public class ModuleFactory {

    public Executable getModuleExecuter(String optionName)
    {
        switch (optionName) {
            case "loadData":
                return new LoadDataExecuter();
            case "filter":
                return new FilterColumnsExecuter();
            case "rename":
                return new RenameColumnsExecuter();
            case "add":
                return  new AddColumnsExecuter();
            case "remove":
                return new RemoveColumnsExecuter();
            case "concat":
                return new ConcatColumnsExecuter();
            case "union":
                return new UnionExecuter();
            default:
                throw new InputMismatchException("Invalid option: " + optionName);
        }
    }
}
