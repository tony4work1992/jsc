package smartosc.jec.ecommerce.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

    public static void writeCSV(String fileName, List<String[]> data, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append))) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> readCSV(String fileName) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
