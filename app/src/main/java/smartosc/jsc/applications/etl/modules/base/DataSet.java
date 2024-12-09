package smartosc.jsc.applications.etl.modules.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSet {

    // List of Map<String, String> to store the data.
    private List<Map<String, String>> data;

    private ArrayList<String> headers;


    // Getter for data.
    public List<Map<String, String>> getData() {
        return data;
    }

    public DataSet() {}


    public DataSet(List<Map<String, String>> data, ArrayList<String> headers) {
        this.data = data;
        this.headers = headers;
    }

    // Setter for data.
    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }

    public void setHeaders(ArrayList<String> headers) {
        this.headers = headers;
    }

    public ArrayList<String> getHeaders() {
        return this.headers;
    }
}
