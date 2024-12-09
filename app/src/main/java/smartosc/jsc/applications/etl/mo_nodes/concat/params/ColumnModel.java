package smartosc.jsc.applications.etl.mo_nodes.concat.params;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.List;

@Data
public class ColumnModel {
    private String newColumn;
    private String columnValue;
}
