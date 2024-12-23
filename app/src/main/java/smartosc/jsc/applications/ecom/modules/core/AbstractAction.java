package smartosc.jsc.applications.ecom.modules.core;

import java.io.IOException;

public abstract class AbstractAction {
    protected Boolean keepRunning = true;

    public abstract void execute() throws IOException;
}
