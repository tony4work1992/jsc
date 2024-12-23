package smartosc.jsc.applications.ecommerce.utils;


import smartosc.jsc.applications.ecommerce.products.models.Product;
import smartosc.jsc.applications.ecommerce.promotion.models.Promotion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileUtils {

    public static ArrayList<String> loadDataFromFile(String filePath) {
        ArrayList<String> items = new ArrayList<>();
        Path path = Paths.get(filePath);
        System.out.println(path);
        if(!Files.exists(path)) {
            System.out.println("This file doesn't exist " + filePath);
        }
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return items;
    }

    public static void saveDataToFile(String folderPath, String filePath, ArrayList<String> content) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean result = folder.mkdirs();
            System.out.println("Result create folder" + folderPath + ": " + result);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (String item : content) {
                writer.write(item);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
