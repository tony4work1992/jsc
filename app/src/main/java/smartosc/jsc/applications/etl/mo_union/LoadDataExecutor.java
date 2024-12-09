package smartosc.jsc.applications.etl.mo_union;

import java.util.List;
import java.util.Map;

public class LoadDataExecutor {
    public List<Map<String, Object>> execute(List<Map<String, Object>> data) {
        System.out.println("Executing LoadData...");
        return data;
    }
}