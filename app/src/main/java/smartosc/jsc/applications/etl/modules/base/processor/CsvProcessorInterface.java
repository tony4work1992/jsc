package smartosc.jsc.applications.etl.modules.base.processor;

import smartosc.jsc.applications.etl.modules.base.DataSet;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;

public interface CsvProcessorInterface {

    public DataSet execute(AbstractInstruction instruction, DataSet dataSet);
}