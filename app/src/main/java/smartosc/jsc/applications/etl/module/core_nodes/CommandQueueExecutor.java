package smartosc.jsc.applications.etl.module.core_nodes;

import java.util.LinkedHashMap;
import java.util.Map;

import smartosc.jsc.applications.etl.module.ba_nodes.Executable;

public class CommandQueueExecutor {
    private Map<Executable, String> commandQueue = new LinkedHashMap<>();
    
    public void pushToCommandQueueExecutor(Executable module, String params)
    {
        this.commandQueue.put(module, params);
    }

    public Map<Executable, String> getCommandQueueExecutor()
    {
        return this.commandQueue;
    }
}
