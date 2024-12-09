package smartosc.jsc.applications.etl.modules.mo_rename;

import java.util.Map;

import smartosc.jsc.applications.etl.modules.base.AbstractEtlFactory;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;
import smartosc.jsc.applications.etl.modules.mo_rename.instruction.RenameInstruction;
import smartosc.jsc.applications.etl.modules.mo_rename.processor.RenameProcessor;

public class RenameFactory extends AbstractEtlFactory{

    @Override
    public CsvProcessorInterface createProcessor() {
        return new RenameProcessor();
    }

    @Override
    public AbstractInstruction createInstruction(Map<String, String> data) {
        String oldColumnName = data.get(RenameInstruction.OLD_COL_NAME);
        String newColumnName = data.get(RenameInstruction.NEW_COL_NAME);
        return new RenameInstruction(oldColumnName, newColumnName);
    }
}
