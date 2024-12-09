package smartosc.jsc.applications;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import smartosc.jsc.applications.etl.ba_factory.ExecuterFactory;
import smartosc.jsc.applications.etl.ba_nodes.Executable;

public class App {

    public static void main(String[] args) {
        String dataset = "[{\"name\": \"KhaiTD\", \"gender\": \"Male\", \"age\" : 18},{\"name\": \"KhaiTD2\", \"gender\": \"Male\",\"age\" : 26},{\"name\": \"NAME3\", \"gender\": \"Male\",\"age\" : 30}]";
        // @TODO The params should be extracted from the request
        String addColumns = "[{\"column\": \"email\", \"value\": \"khaitd@smartosc.com\"},{\"column\": \"company\", \"value\": \"SmartOSC\"}]";
        String renameColumns = "[{\"oldColumnName\": \"email\", \"newColumnName\": \"email_address\"}]";
        String concatColumns = "[{\"columnAfterConcat\": \"new_concat_column\", \"columns\": [\"name\", \"company\"]}]";
        String removeColumns = "[\"gender\"]";
        String filterValues = "[{\"columnName\" :\"age\", \"condition\":\">\", \"value\":19},{\"columnName\" :\"name\", \"condition\":\"startsWith\", \"value\":\"KhaiTD2\"}]";
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonDataset = mapper.readTree(dataset);

            // @TODO Use Factory Pattern to get executer based on operation type
            // Add column
            Executable addColumnsExecuter = ExecuterFactory.getExecuter("add_columns");
            JsonNode returnDataAddColumn = addColumnsExecuter.execute(addColumns, jsonDataset);
            String updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataAddColumn);
            System.out.println("-----------Data After Add Column--------------");
            System.out.println(updatedJson);

            // Rename Column
            Executable renameColumnsExecuter = ExecuterFactory.getExecuter("rename_columns");
            JsonNode returnDataRenameColumn = renameColumnsExecuter.execute(renameColumns, returnDataAddColumn);
            updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataRenameColumn);
            System.out.println("-----------Data After Rename Column--------------");
            System.out.println(updatedJson);

            // Concat Column
            Executable concatColumnsExecuter = ExecuterFactory.getExecuter("concat_columns");
            JsonNode returnDataconcatColumn = concatColumnsExecuter.execute(concatColumns, returnDataRenameColumn);
            updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataconcatColumn);
            System.out.println("-----------Data After Concat Column--------------");
            System.out.println(updatedJson);

            // Remove Column
            Executable removeColumnsExecuter = ExecuterFactory.getExecuter("remove_columns");
            JsonNode returnDataRemoveColumn = removeColumnsExecuter.execute(removeColumns, returnDataconcatColumn);
            updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataRemoveColumn);
            System.out.println("-----------Data After Remove Column--------------");
            System.out.println(updatedJson);

            // Filter Values
            Executable filterValuesExecuter = ExecuterFactory.getExecuter("filter_values");
            JsonNode returnDataFilterColumn = filterValuesExecuter.execute(filterValues, returnDataRemoveColumn);
            updatedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnDataFilterColumn);
            System.out.println("-----------Data After Filter Value--------------");
            System.out.println(updatedJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
