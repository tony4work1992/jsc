package smartosc.jsc.applications.etl.mo_commanders;

import lombok.AllArgsConstructor;
import lombok.Data;
import smartosc.jsc.applications.etl.constants.TransformerType;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.Executable;
import smartosc.jsc.applications.etl.mo_transformer.executors.ba_nodes.IParamsModel;

import java.util.List;

@Data
@AllArgsConstructor
public class TransformerCommand {
    private Executable transformer;
    private List<IParamsModel> params;
    private int id;
    private int parentId;

    public TransformerCommand(Executable transformer, List<IParamsModel> params, int id) {
        this.transformer = transformer;
        this.params = params;
        this.id = id;
        this.parentId = -1;
    }
}
