package smartosc.jsc.applications.etl.mo_union;

import java.util.List;
import java.util.Map;

public class UnionConcatExecutor {
    public List<Map<String, Object>> execute(List<Map<String, Object>> data, List<String> columnsToConcat) {
        System.out.println("Executing UnionConcat...");
        String newColumnName = String.join("_", columnsToConcat);

        data.forEach(record -> {
            StringBuilder concatenatedValue = new StringBuilder();
            for (String column : columnsToConcat) {
                if (record.containsKey(column)) {
                    concatenatedValue.append(record.get(column)).append(" ");
                }
            }
            record.put(newColumnName, concatenatedValue.toString().trim());
        });

        return data;
    }
}