package smartosc.jsc.applications.etl.modules.mo_filter;

import java.util.Map;

import smartosc.jsc.applications.etl.modules.base.AbstractEtlFactory;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;
import smartosc.jsc.applications.etl.modules.mo_filter.instruction.FilterInstruction;
import smartosc.jsc.applications.etl.modules.mo_filter.processcor.FilterProcessor;

public class FilterFactory extends AbstractEtlFactory
{

    @Override
    public CsvProcessorInterface createProcessor() {
        return new FilterProcessor();
    }

    @Override
    public AbstractInstruction createInstruction(Map<String, String> data) {
        String columnName = data.get(FilterInstruction.COL_NAME);
        String operation = data.get(FilterInstruction.OPERATION);
        String value = data.get(FilterInstruction.VALUE);
        return new FilterInstruction(columnName, operation, value);
    }

}
