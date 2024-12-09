package smartosc.jsc.applications.etl.ba_nodes;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public interface ExerciseInterface {
    Map<Integer, JsonNode> execute(String nodes) throws Exception;
}