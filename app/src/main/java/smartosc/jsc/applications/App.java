/*
 * This source file was generated by the Gradle 'init' task
 */
package smartosc.jsc.applications;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.mo_add_columns.AddColumnsExecuter;
import smartosc.jsc.applications.etl.mo_rename_columns.RenameColumnsExecuter;
import smartosc.jsc.applications.etl.mo_remove_columns.RemoveColumnsExecuter;
import smartosc.jsc.applications.etl.mo_concat_columns.ConcatColumnsExcuter;
import smartosc.jsc.applications.etl.mo_filter_values.FiltterValuesExecuter;;

public class App {

    public static void main(String[] args) {
        String dataset = "[{\"name\": \"KhaiTD\", \"gender\": \"Male\", \"age\" : 18},{\"name\": \"KhaiTD2\", \"gender\": \"Male\",\"age\" : 26},{\"name\": \"NAME3\", \"gender\": \"Male\",\"age\" : 30}]";
        // @TODO The params should be extracted from the request
        String addColumns = "[{\"column\": \"email\", \"value\": \"khaitd@smartosc.com\"},{\"column\": \"company\", \"value\": \"SmartOSC\"}]";
        String renameColumns = "[{\"oldColumnName\": \"email\", \"newColumnName\": \"email_address\"},{\"oldColumnName\": \"age\", \"newColumnName\": \"years_old\"}]";
        String concatColumns = "[{\"columnAfterConcat\": \"new_concat_column\", \"columns\": [\"name\", \"company\"]}]";
        String removeColumns = "[\"gender\"]";
        String filterValues = "[{\"columnName\" :\"age\", \"condition\":\">\", \"value\":19},{\"columnName\" :\"name\", \"condition\":\"startsWith\", \"value\":\"KhaiTD2\"}]";
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonDataset = mapper.readTree(dataset);

            AddColumnsExecuter addColumnsExecuter = new AddColumnsExecuter();
            RenameColumnsExecuter renameColumnsExecuter = new RenameColumnsExecuter();
            ConcatColumnsExcuter concatColumnsExcuter = new ConcatColumnsExcuter();
            RemoveColumnsExecuter removeColumnsExecuter = new RemoveColumnsExecuter();
            FiltterValuesExecuter filtterValuesExecuter = new FiltterValuesExecuter();

            // @TODO Factory pattern will be used to create the executer

            //Add column
            JsonNode returnDataAddColumn = addColumnsExecuter.execute(addColumns, jsonDataset);
            String updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataAddColumn);
            System.out.println("-----------Data After Add Column--------------");
            System.out.println(updatedJson);

            //Rename Column
            JsonNode returnDataRenameColumn = renameColumnsExecuter.execute(renameColumns, returnDataAddColumn);
            updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataRenameColumn);
            System.out.println("-----------Data After Rename Column--------------");
            System.out.println(updatedJson);

            //Concat Column
            JsonNode returnDataconcatColumn = concatColumnsExcuter.execute(concatColumns, returnDataAddColumn);
            updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataconcatColumn);
            System.out.println("-----------Data After Concat Column--------------");
            System.out.println(updatedJson);

            //Remove Column
            JsonNode returnDataRemoveColumn = removeColumnsExecuter.execute(removeColumns, returnDataAddColumn);
            updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataRemoveColumn);
            System.out.println("-----------Data After Remove Column--------------");
            System.out.println(updatedJson);

            //Filter Value Columns
            JsonNode returnDataFilterColumn = filtterValuesExecuter.execute(filterValues, jsonDataset);
            updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataFilterColumn);
            System.out.println("-----------Data After Filter Value--------------");
            System.out.println(updatedJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
