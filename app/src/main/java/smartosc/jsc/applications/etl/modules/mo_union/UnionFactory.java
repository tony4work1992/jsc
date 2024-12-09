package smartosc.jsc.applications.etl.modules.mo_union;

import smartosc.jsc.applications.etl.modules.base.AbstractEtlFactory;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;
import smartosc.jsc.applications.etl.modules.mo_union.instruction.UnionInstruction;
import smartosc.jsc.applications.etl.modules.mo_union.processor.UnionProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class UnionFactory extends AbstractEtlFactory {

    private UnionProcessor processor;

    @Override
    public CsvProcessorInterface createProcessor() {
        if (this.processor == null) {
            this.processor = new UnionProcessor();
        }
        return this.processor;
    }

    @Override
    public AbstractInstruction createInstruction(Map<String, String> data) {
        String parentIds = data.get(UnionInstruction.UNION_IDS);
        String[] parentIdsArray = parentIds.split(",");
        ArrayList<String> parentIdsList = new ArrayList<>(Arrays.asList(parentIdsArray));
        return new UnionInstruction(parentIdsList);
    }
}
