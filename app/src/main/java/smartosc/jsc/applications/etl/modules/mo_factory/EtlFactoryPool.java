package smartosc.jsc.applications.etl.modules.mo_factory;

import smartosc.jsc.applications.etl.modules.base.AbstractEtlFactory;
import smartosc.jsc.applications.etl.modules.mo_add.AddFactory;
import smartosc.jsc.applications.etl.modules.mo_concat.ConcatFactory;
import smartosc.jsc.applications.etl.modules.mo_filter.FilterFactory;
import smartosc.jsc.applications.etl.modules.mo_remove.RemoveFactory;
import smartosc.jsc.applications.etl.modules.mo_rename.RenameFactory;
import smartosc.jsc.applications.etl.modules.mo_union.UnionFactory;

import java.util.HashMap;
import java.util.Map;

public class EtlFactoryPool {
    public static final String FILTER = "Filter";
    public static final String RENAME = "RenameColumns";
    public static final String REMOVE = "Remove";
    public static final String ADD = "AddColumns";
    public static final String CONCAT = "Concat";
    public static final String UNION = "Union";

    private static Map<String, AbstractEtlFactory> etlFactoriesSingleton = new HashMap<>();

    public static AbstractEtlFactory getEtlFactory(String transformType)  {
        return switch (transformType) {
            case FILTER -> new FilterFactory();
            case RENAME -> new RenameFactory();
            case REMOVE -> new RemoveFactory();
            case ADD -> new AddFactory();
            case CONCAT -> new ConcatFactory();
            case UNION -> getEtlFactoryFromSingletonPool(UNION);
            default -> throw new IllegalArgumentException("Invalid transform type: " + transformType);
        };
    }

    private static void addEtlFactory(String type, AbstractEtlFactory etlFactory) {
        etlFactoriesSingleton.put(type, etlFactory);
    }

    private static AbstractEtlFactory getEtlFactoryFromSingletonPool(String type) {
        if (!etlFactoriesSingleton.containsKey(type)) {
            switch (type) {
                case UNION -> addEtlFactory(UNION, new UnionFactory());
                default -> throw new IllegalArgumentException("Invalid transform type: " + type);
            }
        }
        return etlFactoriesSingleton.get(type);
    }
}
