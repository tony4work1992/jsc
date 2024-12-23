package smartosc.jsc.applications.jec;

import smartosc.jsc.applications.jec.commands.services.CommandService;

public class App {
 // Jec app
    public static void main(String[] args) throws Exception {
        CommandService commandService = new CommandService();
        commandService.action();
       
    }
}
