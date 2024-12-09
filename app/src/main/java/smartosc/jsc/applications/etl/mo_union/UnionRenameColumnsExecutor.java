package smartosc.jsc.applications.etl.mo_union;

import java.util.List;
import java.util.Map;

public class UnionRenameColumnsExecutor {
    public List<Map<String, Object>> execute(List<Map<String, Object>> data, Map<String, String> renameMapping) {
        System.out.println("Executing UnionRenameColumns...");
        data.forEach(record -> {
            renameMapping.forEach((oldKey, newKey) -> {
                if (record.containsKey(oldKey)) {
                    record.put(newKey, record.remove(oldKey));
                }
            });
        });
        return data;
    }
}