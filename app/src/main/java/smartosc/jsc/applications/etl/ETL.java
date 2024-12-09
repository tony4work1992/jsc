package smartosc.jsc.applications.etl;

import smartosc.jsc.applications.etl.mo_commanders.TransformerCommand;
import smartosc.jsc.applications.etl.mo_extractor.Extractor;
import smartosc.jsc.applications.etl.mo_loader.Loader;
import smartosc.jsc.applications.etl.mo_transformer.Transformer;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class ETL {
    Extractor extractor;
    Transformer transformer;
    Loader loader;
    public ETL() {
        this.extractor = new Extractor();
        this.transformer = new Transformer();
        this.loader = new Loader();
    }

    public void execute (String dataset, List<TransformerCommand> commandsQueue) {
        try {
            JsonNode extractedData = this.extractor.extractData(dataset);
            JsonNode convertedData = this.transformer.transform(extractedData, commandsQueue);
            this.loader.load(convertedData);
        } catch (Exception e) {
            System.err.println("Error when process data: " + e.getMessage());
        }
    }
}
