package smartosc.jsc.applications.etl.modules.mo_rename.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import smartosc.jsc.applications.etl.modules.base.DataSet;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;
import smartosc.jsc.applications.etl.modules.mo_rename.instruction.RenameInstruction;

public class RenameProcessor implements CsvProcessorInterface {

    public DataSet execute(AbstractInstruction instruction, DataSet dataSet) {
        RenameInstruction renameInstruction = (RenameInstruction) instruction;
        String oldColName = renameInstruction.getOldColName();
        String newColName = renameInstruction.getNewColName();

        ArrayList<String> headers = dataSet.getHeaders();
        int index = headers.indexOf(oldColName);
        headers.set(index, newColName);
        dataSet.setHeaders(headers);

        List<Map<String, String>> data = dataSet.getData();
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> row = data.get(i);
            row.put(newColName, row.get(oldColName));
            row.remove(oldColName);
            data.set(i, row);
        }
        return dataSet;
    }
    
}
