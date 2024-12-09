package smartosc.jsc.applications.etl.modules.mo_concat;

import java.util.Map;

import smartosc.jsc.applications.etl.modules.base.AbstractEtlFactory;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;
import smartosc.jsc.applications.etl.modules.mo_concat.instruction.ConcatInstruction;
import smartosc.jsc.applications.etl.modules.mo_concat.processor.ConcatProcessor;

public class ConcatFactory extends AbstractEtlFactory {

    @Override
    public CsvProcessorInterface createProcessor() {
        return new ConcatProcessor();
    }

    @Override
    public AbstractInstruction createInstruction(Map<String, String> data) {
        String newColName = data.get(ConcatInstruction.NEW_COL_NAME);
        // Split the column names by comma to an array
        String[] concatColNames = data.get(ConcatInstruction.CONCAT_COLS_NAME).split(",");
        return new ConcatInstruction(newColName, concatColNames);
    }
}
