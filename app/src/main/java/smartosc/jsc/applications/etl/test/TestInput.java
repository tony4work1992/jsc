package smartosc.jsc.applications.etl.test;

import java.util.ArrayList;
import java.util.Map;


import java.util.List;

public class TestInput {
    public static final String TEST_CSV_INPUT = "app/src/main/java/smartosc/jsc/applications/etl/test/resources/test_input.csv";

    public static final String TEST_CSV_INPUT_2 = "app/src/main/java/smartosc/jsc/applications/etl/test/resources/test_input_2.csv";

    public static final String TEST_CSV_OUTPUT = "app/src/main/java/smartosc/jsc/applications/etl/test/resources/test_output.csv";

    public static final String TEST_JSON_INSTRUCTION_INPUT = "app/src/main/java/smartosc/jsc/applications/etl/test/resources/test_instruction_input.json";
    public static final String TEST_JSON_INSTRUCTION_INPUT_2 = "app/src/main/java/smartosc/jsc/applications/etl/test/resources/test_instruction_input_2.json";

    public static final ArrayList<Map<String, Map<String, String>>> TEST_DATA = new ArrayList<>(List.of(
        Map.of(
            "filter", Map.of(
                "col_name", "userId",
                "operation", "eq",
                "value", "1"
            )
        ),
        Map.of(
            "rename", Map.of(
                "old_col_name", "userId",
                "new_col_name", "user_Id"
            )
        ),
        Map.of(
            "remove", Map.of(
                "col_name", "user_Id"
            )
        ),
        Map.of(
            "add", Map.of(
                "col_name", "test_col"
            )
        ),
        Map.of(
            "add", Map.of(
                "col_name", "test_col_2",
                "default_value", "test"
            )
        ),
        Map.of(
            "concat", Map.of(
                "new_col_name", "test_concat_col_3",
                "concat_cols_name", "test_col_2,title"
            )
        )
    ));
}
