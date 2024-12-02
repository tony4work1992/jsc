/*
 * This source file was generated by the Gradle 'init' task
 */
package smartosc.jsc.applications;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.mo_concat_columns.ConcatColumnsExecuter;
import smartosc.jsc.applications.etl.mo_remove_colums.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExecuter;

public class App {

    public static void main(String[] args) {
        String dataset = "[{\"name\": \"Nghianht\", \"gender\": \"Male\", \"address\": \"Hanoi\", \"country\": \"Vietnam\"}]";
        // @TODO The params should be extracted from the request
        String addColumns1 = "[{\"column\": \"email\", \"value\": \"nghianht@smartosc.com\"}]";
        String addColumns2 = "[{\"column\": \"age\", \"value\": \"28\"}]";
        String renameColumns = "[{\"oldColumnName\": \"email\", \"newColumnName\": \"emp_email\"}," +
                "{\"oldColumnName\": \"age\", \"newColumnName\": \"emp_age\"}]";
        String removeColumns = "[\"address\",\"country\"]";
        String concatColumns = "[{\"concatColumnName\": \"gender_age\", \"concatColumns\": [\"gender\",\"emp_age\"]}]";
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonDataset = mapper.readTree(dataset);

            //Add columns
            AddColumnsExecuter addColumnsExecuter = new AddColumnsExecuter();
            JsonNode returnData = addColumnsExecuter.execute(addColumns1, jsonDataset);
            returnData = addColumnsExecuter.execute(addColumns2, returnData);
            String updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnData);
            System.out.println("Result after add columns:");
            System.out.println(updatedJson);
            System.out.println("-------------------------");

            //Rename columns
            RenameColumnsExecuter renameColumnsExecuter = new RenameColumnsExecuter();
            returnData = renameColumnsExecuter.execute(renameColumns, returnData);
            String renameDataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnData);
            System.out.println("Result after rename columns:");
            System.out.println(renameDataJson);
            System.out.println("-------------------------");

            //Remove columns
            RemoveColumnsExecuter removeColumnsExecuter = new RemoveColumnsExecuter();
            returnData = removeColumnsExecuter.execute(removeColumns, returnData);
            String removeDataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnData);
            System.out.println("Result after remove columns:");
            System.out.println(removeDataJson);
            System.out.println("-------------------------");

            //Concat columns
            ConcatColumnsExecuter concatColumnsExecuter = new ConcatColumnsExecuter();
            returnData = concatColumnsExecuter.execute(concatColumns, returnData);
            String concatDataJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnData);
            System.out.println("Result after concat columns:");
            System.out.println(concatDataJson);
            System.out.println("-------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
