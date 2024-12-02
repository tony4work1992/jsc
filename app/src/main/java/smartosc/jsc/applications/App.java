/*
 * This source file was generated by the Gradle 'init' task
 */
package smartosc.jsc.applications;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.mo_concat_columns.ConcatColumnsExecuter;
import smartosc.jsc.applications.etl.mo_filter_params.FilterExecuter;
import smartosc.jsc.applications.etl.mo_remove_columns.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.params.RenameColumnsParamsExtractor;
import smartosc.jsc.applications.etl.utils.seed_data.Person;
import smartosc.jsc.applications.etl.utils.seed_data.SeedData;

import java.io.InputStream;
import java.util.List;

public class App {

    public static void main(String[] args) {
        List<Person> dataset = SeedData.generateSeedData();


        String addColumnsSuccess = "[{\"column\": \"status\", \"value\": \"Active\"}]";

        String removeColumnsSuccess = "[{\"column\": \"gender\"}]";
        String removeColumnsFailed = "[{\"column\": \"gender_failed\"}]";

        String renameColumnsSuccess = "[{\"currentColumn\": \"name\", \"newColumn\": \"full_name\"}]";
        String renameColumnsFailed = "[{\"currentColumn\": \"name_failed\", \"newColumn\": \"full_name\"}]";

        String concatColumnsSuccess = "[{\"currentColumns\": [\"name\",\"age\"], \"newColumn\": \"info\"}]";
        String concatColumnsFailed = "[{\"currentColumns\": [\"name\",\"age_failed\"], \"newColumn\": \"info\"}]";

        String filterColumnsSuccess = "[{\"column\": \"age\", \"value\": \"25\", \"operator\": \"=\"}]";
        String filterColumnsFailed = "[{\"column\": \"age_failed\", \"value\": \"25\", \"operator\": \"=\"}]";

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(dataset);
        try {
            JsonNode jsonDataset = mapper.readTree(dataset.toString());

            AddColumnsExecuter addColumnsExecuter = new AddColumnsExecuter();
            RemoveColumnsExecuter removeColumnsExecuter = new RemoveColumnsExecuter();
            ConcatColumnsExecuter concatColumnsExecuter = new ConcatColumnsExecuter();
            RenameColumnsExecuter renameColumnsExecuter = new RenameColumnsExecuter();
            FilterExecuter filterExecuter = new FilterExecuter();

//            // add columns
            JsonNode returnDataAddColumn = addColumnsExecuter.execute(addColumnsSuccess, jsonDataset);
            String updatedJsonAddColumn = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataAddColumn);
            System.out.println("Add column success" + updatedJsonAddColumn);
//
            // remove columns
            JsonNode returnDataRemoveSuccess = removeColumnsExecuter.execute(removeColumnsSuccess, jsonDataset);
            String updatedJsonRemoveSuccess = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataRemoveSuccess);
            System.out.println("Remove column success" + updatedJsonRemoveSuccess);
//
//            JsonNode returnDataRemoveFailed = removeColumnsExecuter.execute(removeColumnsFailed, jsonDataset);
//            String updatedJsonRemoveFailed = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataRemoveFailed);
//            System.out.println(updatedJsonRemoveFailed);

            // rename columns
            JsonNode returnDataRenameSuccess = renameColumnsExecuter.execute(renameColumnsSuccess, jsonDataset);
            String updatedJsonRenameSuccess = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataRenameSuccess);
            System.out.println("Rename column success" +updatedJsonRenameSuccess);

//            JsonNode returnDataRenameFailed = renameColumnsExecuter.execute(renameColumnsFailed, jsonDataset);
//            String updatedJsonRenameFailed = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataRenameFailed);
//            System.out.println(updatedJsonRenameFailed);

            // concat columns
            JsonNode returnDataConcatSuccess = concatColumnsExecuter.execute(concatColumnsSuccess, jsonDataset);
            String updatedJsonConcatSuccess = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataConcatSuccess);
            System.out.println("Concat column success" +updatedJsonConcatSuccess);
//
//            JsonNode returnDataConcatFailed = concatColumnsExecuter.execute(concatColumnsFailed, jsonDataset);
//            String updatedJsonConcatFailed = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataConcatFailed);
//            System.out.println(updatedJsonConcatFailed);
//
//            // filter columns
            JsonNode returnDataFilterSuccess = filterExecuter.execute(filterColumnsSuccess, jsonDataset);
            String updatedJsonFilterSuccess = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataFilterSuccess);
            System.out.println("Filter column success" +updatedJsonFilterSuccess);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
