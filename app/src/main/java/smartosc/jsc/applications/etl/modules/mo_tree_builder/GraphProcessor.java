package smartosc.jsc.applications.etl.modules.mo_tree_builder;

import smartosc.jsc.applications.etl.modules.base.DataSet;
import smartosc.jsc.applications.etl.modules.base.instruction.AbstractInstruction;
import smartosc.jsc.applications.etl.modules.mo_factory.EtlFactoryPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphProcessor {
    private ArrayList<DataSet> dataSets = new ArrayList<>();

    public void processInstructionGraph(AbstractInstruction root, DataSet dataSet) {
        if (root == null) {
            return;
        }
        System.out.println(root.getName());

        dataSet = EtlFactoryPool.getEtlFactory(root.getName()).createProcessor().execute(root, dataSet);
        if ((Objects.equals(root.getName(), EtlFactoryPool.UNION) && dataSet == null) || dataSet.getData().isEmpty()) {
            return;
        }
        List<AbstractInstruction> children = root.getChildren();
        if (children.isEmpty()) {
            this.dataSets.add(dataSet);
        }
        for (AbstractInstruction child : children) {
            this.processInstructionGraph(child, dataSet);
        }
    }

    public ArrayList<DataSet> getDatasets() {
        return this.dataSets;
    }
}
