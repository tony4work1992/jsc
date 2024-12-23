package smartosc.jsc.applications.ecom;

import smartosc.jsc.applications.ecom.modules.core.MainMenu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        MainMenu mainMenu = new MainMenu();
        mainMenu.execute();
    }
}
