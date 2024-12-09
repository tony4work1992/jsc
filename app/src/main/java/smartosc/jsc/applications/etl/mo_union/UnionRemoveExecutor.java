package smartosc.jsc.applications.etl.mo_union;

import java.util.List;
import java.util.Map;

public class UnionRemoveExecutor {
    public void execute(List<Map<String, Object>> data, String columnName) {
        data.forEach(record -> record.remove(columnName));
    }
}