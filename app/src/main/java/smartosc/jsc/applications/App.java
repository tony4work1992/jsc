/*
 * This source file was generated by the Gradle 'init' task
 */
package smartosc.jsc.applications;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;

public class App {

    public static void main(String[] args) {
        String dataset = "[{\"name\": \"AnNM1\", \"gender\": \"Male\"}]";
        // @TODO The params should be extracted from the request
        String addColumns1 = "[{\"column\": \"email\", \"value\": \"annm1@smartosc.com\"}]";
        String addColumns2 = "[{\"column\": \"age\", \"value\": \"11\"}]";
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonDataset = mapper.readTree(dataset);

            AddColumnsExecuter addColumnsExecuter = new AddColumnsExecuter();
            // @TODO Factory pattern will be used to create the executer
            JsonNode returnData1 = addColumnsExecuter.execute(addColumns1, jsonDataset);
            JsonNode returnData2 = addColumnsExecuter.execute(addColumns2, returnData1);
            String updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnData2);
            System.out.println(updatedJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
