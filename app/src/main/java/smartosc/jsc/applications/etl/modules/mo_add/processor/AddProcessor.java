package smartosc.jsc.applications.etl.modules.mo_add.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import smartosc.jsc.applications.etl.modules.mo_add.instruction.AddInstruction;
import smartosc.jsc.applications.etl.modules.base.DataSet;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;

public class AddProcessor implements CsvProcessorInterface {

    public DataSet execute(AbstractInstruction instruction, DataSet dataSet) {
        AddInstruction addInstruction = (AddInstruction) instruction;
        String addColumnName = addInstruction.getColName();
        ArrayList<String> headers = dataSet.getHeaders();
        headers.add(addColumnName);
        dataSet.setHeaders(headers);

        List<Map<String, String>> data = dataSet.getData();
        for (int i = 0; i < data.size(); i++) {
            Map<String, String> row = data.get(i);
            row.put(addColumnName, addInstruction.getDefaultValue());
            data.set(i, row);
        }
        dataSet.setData(data);
        return dataSet;
    }
    
}
