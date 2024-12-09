package smartosc.jsc.applications.etl.ba_nodes;

import smartosc.jsc.applications.etl.mo_nodes.add.AddNode;
import smartosc.jsc.applications.etl.mo_nodes.load.LoadNode;
import smartosc.jsc.applications.etl.mo_nodes.union.UnionNode;
import smartosc.jsc.applications.etl.mo_nodes.filter.FilterNode;
import smartosc.jsc.applications.etl.mo_nodes.remove.RemoveNode;
import smartosc.jsc.applications.etl.mo_nodes.rename.RenameNode;
import smartosc.jsc.applications.etl.mo_nodes.concat.ConcatNode;
import smartosc.jsc.applications.etl.ba_nodes.constants.NodeExecutor;

public class NodeFactory {

    public Node createNode(String nodeExecutor) throws Exception {
        switch (nodeExecutor) {
            case NodeExecutor.LOAD:
                return new LoadNode();
            case NodeExecutor.ADD:
                return new AddNode();
            case NodeExecutor.REMOVE:
                return new RemoveNode();
            case NodeExecutor.FILTER:
                return new FilterNode();
            case NodeExecutor.RENAME:
                return new RenameNode();
            case NodeExecutor.CONCAT:
                return new ConcatNode();
            case NodeExecutor.UNION:
                return new UnionNode();
            default:
                throw new RuntimeException("Unknown node type: " + nodeExecutor);
        }
    }
}
