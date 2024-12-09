package smartosc.jsc.applications.etl.modules.mo_remove.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import smartosc.jsc.applications.etl.modules.base.DataSet;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;
import smartosc.jsc.applications.etl.modules.mo_remove.instruction.RemoveInstruction;
import smartosc.jsc.applications.etl.utils.Validator;

public class RemoveProcessor implements CsvProcessorInterface {

    public DataSet execute(AbstractInstruction instruction, DataSet dataSet) {
        RemoveInstruction removeInstruction = (RemoveInstruction) instruction;
        String removeColumnName = removeInstruction.getRemoveColName();
        ArrayList<String> headers = dataSet.getHeaders();

        Validator.validateColName(removeColumnName, headers);

        headers.remove(removeColumnName);
        dataSet.setHeaders(headers);

        List<Map<String, String>> data = dataSet.getData();
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> row = data.get(i);
            row.remove(removeColumnName);
            data.set(i, row);
        }
        dataSet.setData(data);
        return dataSet;
    }
 
}
