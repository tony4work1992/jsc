/*
 * This source file was generated by the Gradle 'init' task
 */
package smartosc.jsc.applications;

import smartosc.jsc.applications.etl.ETLApp;
import smartosc.jsc.applications.ecom.EComApp;

public class App {
    public static void main(String[] args) {
        //ETLApp();
        //EComApp();
    }

    public static void ETLApp() {
        ETLApp etlApp = new ETLApp();
        etlApp.runApp();
    }

    public static void EComApp() {
        EComApp ecomApp = new EComApp();
        ecomApp.runApp();
    }
}