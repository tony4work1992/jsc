package smartosc.jsc.applications;

import java.util.ArrayList;
import java.util.Map;

import smartosc.jsc.applications.etl.modules.base.DataSet;
import smartosc.jsc.applications.etl.modules.mo_tree_builder.GraphBuilder;
import smartosc.jsc.applications.etl.modules.mo_tree_builder.GraphProcessor;
import smartosc.jsc.applications.etl.modules.mo_tree_builder.InstructionGraph;
import smartosc.jsc.applications.etl.utils.CsvHandler;
import smartosc.jsc.applications.etl.test.TestInput;

public class App {

    public static void main(String[] args) {
        // Read the CSV file.
        DataSet dataSet = CsvHandler.readCsv(System.getProperty("user.dir") + "/" + TestInput.TEST_CSV_INPUT_2);
        // Get test instructions from TestInput class.
        InstructionGraph instructionGraph = GraphBuilder.buildGraph();

        System.out.println("Processing test instructions...");
        GraphProcessor graphProcessor = new GraphProcessor();
        graphProcessor.processInstructionGraph(instructionGraph.getRoot(), dataSet);
        ArrayList<DataSet> dataSets = graphProcessor.getDatasets();
        for (int i = 0; i < dataSets.size(); i++) {
            System.out.println("Data set " + i + ": ");
            for (Map<String, String> row : dataSets.get(i).getData()) {
                System.out.println(row);
            }
        }
//         Write the data to a new CSV file.
//        CsvHandler.writeCsv(TestInput.TEST_CSV_OUTPUT, dataSet);
    }
}
