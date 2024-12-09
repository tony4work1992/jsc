package smartosc.jsc.applications.etl.modules.mo_remove;

import java.util.Map;

import smartosc.jsc.applications.etl.modules.base.AbstractEtlFactory;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;
import smartosc.jsc.applications.etl.modules.mo_remove.instruction.RemoveInstruction;
import smartosc.jsc.applications.etl.modules.mo_remove.processor.RemoveProcessor;

public class RemoveFactory extends AbstractEtlFactory
{

    @Override
    public CsvProcessorInterface createProcessor() {
        return new RemoveProcessor();
    }

    @Override
    public AbstractInstruction createInstruction(Map<String, String> data) {
        String removeColumnName = data.get(RemoveInstruction.COL_NAME);
        return new RemoveInstruction(removeColumnName);
    }
    
}
