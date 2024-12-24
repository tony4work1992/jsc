package smartosc.jsc.applications.ecom.utils;

import java.io.*;

public class FileUtils {

    public static void writeToFile(String fileName, String content) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(content);
        fileWriter.close();
    }

    public static String readFromFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        File file = new File(fileName);
        file.createNewFile();

        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
        }
        bufferedReader.close();
        return content.toString();
    }
}
