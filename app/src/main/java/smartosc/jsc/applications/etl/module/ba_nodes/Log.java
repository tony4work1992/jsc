package smartosc.jsc.applications.etl.module.ba_nodes;

import java.io.IOException;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.logging.Logger;

public class Log {
    // Log file path
    private static final String LOG_FILE_PATH = System.getProperty("user.dir") + "/app/src/main/java/smartosc/jsc/applications/etl/log/info.log";  // Adjust the path if necessary
    private static Logger logger = Logger.getLogger(Log.class.getName());
    private static FileHandler fileHandler;

    // Static block for setting up the logger and file handler
    static {
        try {
            // Initialize the FileHandler once, and use it for all log entries
            fileHandler = new FileHandler(LOG_FILE_PATH, true); // true to append to the log file
            fileHandler.setFormatter(new SimpleFormatter());  // Use simple formatting for log messages
            logger.addHandler(fileHandler);  // Add the file handler to the logger
        } catch (IOException e) {
            System.err.println("Failed to set up file handler for logger: " + e.getMessage());
        }
    }

    // Method to log messages to a file
    protected static void logToFile(String message) {
        // Log the message
        //comment to avoid wasting time during processing
        // logger.info(message);  
    }

    // Method to log details before execution
    public void logBeforeExecute(String option, String params, Map<Integer, JsonNode> listDataSet) {
        logToFile("Start execute " + option);
        logToFile("Param: " + params);

        for (Map.Entry<Integer, JsonNode> data : listDataSet.entrySet()) {
            logToFile("Dataset key:" +data.getKey()+ " - count before execute:" + data.getValue().size());
            logToFile(data.toString());
        }
    }

    // Method to log details before execution
    public void logAfterExecute(String option, Map<Integer, JsonNode> listDataSet) {
        logToFile("End execute " + option);
        for (Map.Entry<Integer, JsonNode> data : listDataSet.entrySet()) {
            logToFile("Dataset key:" +data.getKey()+ " - count after execute:" + data.getValue().size());
            logToFile("String data: " +data.toString());
        }
    }
}
