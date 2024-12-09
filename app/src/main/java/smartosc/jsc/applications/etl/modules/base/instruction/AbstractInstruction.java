package smartosc.jsc.applications.etl.modules.base.instruction;

import smartosc.jsc.applications.etl.modules.base.processor.CsvProcessorInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractInstruction {

    protected Map<String, Map <String, String>> data;

    protected int id = 0;
    protected String name = "";
    protected List<AbstractInstruction> children = new ArrayList<>();
    protected List<AbstractInstruction> parent = new ArrayList<>();
    protected Map<String, String> config = new HashMap<>();
    protected CsvProcessorInterface csvProcessor;

    public Map<String, Map <String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map <String, String>> data) {
        this.data = data;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AbstractInstruction> getChildren() {
        return children;
    }

    public List<AbstractInstruction> getParent() {
        return parent;
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    public void addChild(AbstractInstruction child) {
        this.children.add(child);
    }

    public void addParent(AbstractInstruction parent) {
        this.parent.add(parent);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void toString(Map<String, Map <String, String>> data) {
        System.out.println(data);
    }
}
