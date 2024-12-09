package smartosc.jsc.applications.etl.transform.ba_nodes;

public interface NodeParamsExtractor<T> {

    public T extractParams(String params) throws Exception;
}
