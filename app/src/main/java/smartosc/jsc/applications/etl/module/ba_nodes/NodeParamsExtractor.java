package smartosc.jsc.applications.etl.module.ba_nodes;

public interface NodeParamsExtractor<T> {
    /**
     * Takes a string (params) that represents a JSON-encoded list of ColumnModel objects
     * @param params
     * @return
     * @throws Exception
     */
    public T extractParams(String params) throws Exception;
}
