package smartosc.jsc.applications.etl.ba_factory;

import smartosc.jsc.applications.etl.ba_factory.constants.EtlConstant;
import smartosc.jsc.applications.etl.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.mo_concat_columns.ConcatColumnsExecuter;
import smartosc.jsc.applications.etl.mo_filter_params.FilterExecuter;
import smartosc.jsc.applications.etl.mo_load_datas.LoadExecuter;
import smartosc.jsc.applications.etl.mo_remove_columns.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExecuter;
import smartosc.jsc.applications.etl.mo_union_nodes.UnionNodesExecuter;

public class ExecuterFactory {
    public Executable getExecuter(String executerName) {
        switch (executerName) {
            case EtlConstant.ADD_COLUMN_EXECUTER -> {
                return new AddColumnsExecuter();
            }
            case EtlConstant.CONCAT_COLUMN_EXECUTER -> {
                return new ConcatColumnsExecuter();
            }
            case EtlConstant.FILTER_COLUMN_EXECUTER -> {
                return new FilterExecuter();
            }
            case EtlConstant.UNION_EXECUTER -> {
                return new UnionNodesExecuter();
            }
            case EtlConstant.RENAME_COLUMN_EXECUTER -> {
                return new RenameColumnsExecuter();
            }
            case EtlConstant.LOAD_DATA_EXECUTER -> {
                return new LoadExecuter();
            }
            case EtlConstant.REMOVE_COLUMN_EXECUTER -> {
                return new RemoveColumnsExecuter();
            }
            default -> {
                throw new IllegalArgumentException("Unknown executer name: " + executerName);
            }
        }
    }
}
