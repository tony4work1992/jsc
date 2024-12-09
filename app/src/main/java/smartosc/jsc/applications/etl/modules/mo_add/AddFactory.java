package smartosc.jsc.applications.etl.modules.mo_add;

import java.util.Map;

import smartosc.jsc.applications.etl.modules.mo_add.instruction.AddInstruction;
import smartosc.jsc.applications.etl.modules.mo_add.processor.AddProcessor;
import smartosc.jsc.applications.etl.modules.base.AbstractEtlFactory;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;

public class AddFactory extends AbstractEtlFactory{

    @Override
    public CsvProcessorInterface createProcessor() {
        return new AddProcessor();
    }

    @Override
    public AbstractInstruction createInstruction(Map<String, String> data) {
        String columnName = data.get("col_name");
        String value = data.get("default_value");
        return new AddInstruction(columnName, value);
    }
}
