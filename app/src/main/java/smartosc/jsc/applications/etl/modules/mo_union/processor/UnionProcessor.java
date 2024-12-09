package smartosc.jsc.applications.etl.modules.mo_union.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.modules.base.DataSet;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;
import smartosc.jsc.applications.etl.modules.mo_union.instruction.UnionInstruction;

import java.util.*;

public class UnionProcessor implements CsvProcessorInterface {

    private ArrayList<DataSet> dataSets = new ArrayList<>();

    public DataSet execute(AbstractInstruction instruction, DataSet dataSet) {
        UnionInstruction unionInstruction = (UnionInstruction) instruction;
        List<String> parentIds = unionInstruction.getParentIds();

        this.addDataset(dataSet);
        ArrayList<DataSet> datasets = this.getDatasets();
        if(datasets.size() == parentIds.size()) {
            return this.unionDataSet();
        }
        return null;
    }

    private DataSet unionDataSet() {
        DataSet newDataSet = new DataSet();
        ArrayList<DataSet> dataSets = this.getDatasets();
        newDataSet.setHeaders(dataSets.getFirst().getHeaders());
        List<Map<String, String>> newData = new ArrayList<>();
        for (DataSet dataSet : dataSets) {
            newData.addAll(dataSet.getData());
        }
        try {
            newData = this.removeDuplicates(newData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        newDataSet.setData(newData);
        return newDataSet;
    }

    public List<Map<String, String>> removeDuplicates(List<Map<String, String>> list) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<String> seen = new HashSet<>();
        List<Map<String, String>> result = new ArrayList<>();

        for (Map<String, String> map : list) {
            // Serialize each map to JSON string
            String json = objectMapper.writeValueAsString(map);

            // Add to result only if it's not already in the set
            if (seen.add(json)) {
                result.add(map);
            }
        }
        return result;
    }

    private ArrayList<DataSet> getDatasets() {
        return this.dataSets;
    }

    private void addDataset(DataSet dataSet) {
        this.dataSets.add(dataSet);
    }
}
