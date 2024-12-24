package smartosc.jsc.applications.ecom.ba_nodes;

import smartosc.jsc.applications.App;

import java.io.*;

public class FileManager {
    public BufferedReader getFileReader(String fileName) {
        try {
            return new BufferedReader(new FileReader(getFilePath(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedWriter getFileWriter(String fileName,Boolean append) {
        try {
            return new BufferedWriter(new FileWriter(getFilePath(fileName), append));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getFilePath(String fileName) {
        File file = new File(App.class.getClassLoader().getResource(fileName).getFile());

        return file.getAbsolutePath();
    }
}
