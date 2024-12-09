package smartosc.jsc.applications.etl.ba_nodes;

import com.fasterxml.jackson.databind.JsonNode;

public class NodeCommand {
    public final Integer id;
    public final Node node;
    public final String params;
    public final JsonNode children;
    public final JsonNode parent;
    public final JsonNode dataset;

    public NodeCommand(Integer id, Node node, String params, JsonNode children, JsonNode parent, JsonNode dataset) {
        this.id = id;
        this.node = node;
        this.params = params;
        this.children = children;
        this.parent = parent;
        this.dataset = dataset;
    }
}
