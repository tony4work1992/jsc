package smartosc.jsc.applications.etl.module.mo_load_data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import smartosc.jsc.applications.etl.module.ba_nodes.Executable;
import smartosc.jsc.applications.etl.module.ba_nodes.Log;
import smartosc.jsc.applications.etl.module.mo_load_data.params.ParamsModel;
import smartosc.jsc.applications.etl.module.mo_load_data.params.LoadDataParamsExtractor;

public class LoadDataExecuter implements Executable {

    public static final String MODULE_NAME = "LoadData";
    private static final String DATA_SET_PATH = System.getProperty("user.dir") + "/app/src/main/java/smartosc/jsc/applications/etl/config/DataSet.json";

    private Log log = new Log();

    @Override
    public Map<Integer, JsonNode> execute(String params, Map<Integer, JsonNode> listDataSet) throws Exception {
        LoadDataParamsExtractor extractor = new LoadDataParamsExtractor();
        ParamsModel nodeModel = extractor.extractParams(params);
        String fileType = getFileExtension(DATA_SET_PATH);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataSet = mapper.createArrayNode();
        switch (fileType) {
            case "json":
                dataSet = loadJsonData(DATA_SET_PATH);
                break;
            // case "csv":
            //     // implement later
            //     break;
            default:
                throw new InputMismatchException("Unsupported data set file format: " + fileType);
        }
        
        // set child node dataset value if has child
        for (Integer child : nodeModel.getChildren()) {
            listDataSet.put(child, dataSet);
        }
        
        this.log.logAfterExecute(MODULE_NAME, listDataSet);
        
        return listDataSet;

    }

    private static String getFileExtension(String fullName) {
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    private JsonNode loadJsonData(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            StringBuilder fileContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine().trim());
            }
            return new ObjectMapper().readTree(fileContent.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fileName, e);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON file: " + fileName, e);
        }
    }

}
