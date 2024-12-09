package smartosc.jsc.applications.etl.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;


import smartosc.jsc.applications.etl.modules.base.DataSet;

public class CsvHandler {

    public static DataSet readCsv(String filePath) {
        System.out.println("Reading CSV file from path: " + filePath);
        DataSet dataSet = new DataSet();

        // Create an empty array to store the data.
        List<Map<String, String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the first line to get the headers.
            String line = br.readLine();
            String[] headers = line.split(",");
            ArrayList<String> headerList = new ArrayList<>(Arrays.asList(headers));
            dataSet.setHeaders(headerList);

            while ((line = br.readLine()) != null) {
                String[] valuesArray = line.split(",");
                ArrayList<String> values = new ArrayList<>(Arrays.asList(valuesArray));
                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], values.get(i));
                }
                data.add(row);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing the CSV file.");
        }
        // check if data is not empty
        if (!data.isEmpty()) {
            dataSet.setData(data);
        }

        return dataSet;
    }

    public static void writeCsv(String path, DataSet dataSet) {
        System.out.println("Writing result to path: " + path);
        List<Map<String, String>> dataToWrite = dataSet.getData();
        if (dataToWrite == null) {
            System.out.println("No data to write.");
            return;
        }

        try (java.io.FileWriter writer = new java.io.FileWriter(path)) {
            // Write the headers.
            ArrayList<String> headers = dataSet.getHeaders();
            for (int i = 0; i < headers.size(); i++) {
                writer.append(headers.get(i));
                if (i != headers.size() - 1) {
                    writer.append(",");
                }
            }
            writer.append("\n");

            // Write the data.
            for (Map<String, String> row : dataToWrite) {
                for (String value : row.values()) {
                    writer.append(value);
                    if (value == null || !value.equals(row.values().toArray()[row.size() - 1])) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing the CSV file.");
        }
    }
}
