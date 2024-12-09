package smartosc.jsc.applications.etl.modules.mo_concat.processor;

import java.util.ArrayList;

import smartosc.jsc.applications.etl.modules.base.DataSet;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;
import smartosc.jsc.applications.etl.modules.mo_concat.instruction.ConcatInstruction;
import smartosc.jsc.applications.etl.utils.Validator;

public class ConcatProcessor implements CsvProcessorInterface {

    public DataSet execute(AbstractInstruction instruction, DataSet dataSet) {
        ConcatInstruction concatInstruction = (ConcatInstruction) instruction;
        String newColName = concatInstruction.getNewColName();
        String[] concatColNames = concatInstruction.getConcatColNames();
        ArrayList<String> headers = dataSet.getHeaders();

        for (String colName : concatColNames) {
            Validator.validateColName(colName, headers);
        }
        headers.add(newColName);
        dataSet.setHeaders(headers);

        for (int i = 0; i < dataSet.getData().size(); i++) {
            StringBuilder concatValue = new StringBuilder();
            for (String colName : concatColNames) {
                concatValue.append(" ").append(dataSet.getData().get(i).get(colName));
            }
            dataSet.getData().get(i).put(newColName, concatValue.toString());
        }

        return dataSet;
    }

}
