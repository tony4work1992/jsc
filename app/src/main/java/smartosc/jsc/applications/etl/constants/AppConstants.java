package smartosc.jsc.applications.etl.constants;

public class AppConstants {
    public static final String LOAD_DATA_EXCUTER = "LoadData";
    public static final String RENAME_COLUMNS_EXECUTER = "RenameColumns";
    public static final String CONCAT_VALUES_EXECUTER = "Concat";
    public static final String REMOVE_COLUMN_EXECUTER = "Remove";
    public static final String FILTER_VALUES_EXECUTER = "Filter";
    public static final String ADD_COLUMNS_EXECUTER = "AddColumns";
    public static final String UNION_EXECUTER = "Union";
//    public static final String DATA_SET = "[{\"sku\":\"EXAM.SKU.01\",\"title\":\"Smart Tivi 4K\",\"width\":120,\"height\":40,\"category\":\"TIVI\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.02\",\"title\":\"Smart Tivi 4K Untra Width\",\"width\":120,\"height\":40,\"category\":\"TIVI\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.03\",\"title\":\"Smart Tivi 2K\",\"width\":120,\"height\":40,\"category\":\"TIVI\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.04\",\"title\":\"Smart Tivi 2K Untra Width\",\"width\":120,\"height\":40,\"category\":\"TIVI\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.05\",\"title\":\"Smart Tivi 8K\",\"width\":120,\"height\":40,\"category\":\"TIVI\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.06\",\"title\":\"Smart Tivi 8K Untra Width\",\"width\":120,\"height\":40,\"category\":\"TIVI\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.07\",\"title\":\"Projector 4K\",\"width\":20,\"height\":30,\"category\":\"Projector\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.08\",\"title\":\"Projector 4K Untra Width\",\"width\":20,\"height\":30,\"category\":\"Projector\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.09\",\"title\":\"Projector 2K\",\"width\":20,\"height\":30,\"category\":\"Projector\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.10\",\"title\":\"Projector 2K Untra Width\",\"width\":20,\"height\":30,\"category\":\"Projector\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.11\",\"title\":\"Projector 8K\",\"width\":20,\"height\":30,\"category\":\"Projector\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.12\",\"title\":\"Projector 8K Untra Width\",\"width\":20,\"height\":30,\"category\":\"Projector\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.13\",\"title\":\"Soundbar Bluetooth A\",\"width\":140,\"height\":15,\"category\":\"Soundbar\",\"image\":\"https://lg.com/sku/\",\"country\":\"UK\"},{\"sku\":\"EXAM.SKU.14\",\"title\":\"Soundbar Bluetooth A\",\"width\":140,\"height\":15,\"category\":\"Soundbar\",\"image\":\"https://lg.com/sku/\",\"country\":\"UK\"},{\"sku\":\"EXAM.SKU.15\",\"title\":\"Soundbar Bluetooth A\",\"width\":140,\"height\":15,\"category\":\"Soundbar\",\"image\":\"https://lg.com/sku/\",\"country\":\"UK\"},{\"sku\":\"EXAM.SKU.16\",\"title\":\"Soundbar Bluetooth A\",\"width\":140,\"height\":15,\"category\":\"Soundbar\",\"image\":\"https://lg.com/sku/\",\"country\":\"UK\"},{\"sku\":\"EXAM.SKU.17\",\"title\":\"Soundbar Bluetooth A\",\"width\":140,\"height\":15,\"category\":\"Soundbar\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.18\",\"title\":\"Soundbar Bluetooth A\",\"width\":140,\"height\":15,\"category\":\"Soundbar\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.19\",\"title\":\"Soundbar Bluetooth A\",\"width\":140,\"height\":15,\"category\":\"Soundbar\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.20\",\"title\":\"Soundbar Bluetooth A\",\"width\":140,\"height\":15,\"category\":\"Soundbar\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.21\",\"title\":\"Soundbar Bluetooth A\",\"width\":140,\"height\":15,\"category\":\"Soundbar\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"},{\"sku\":\"EXAM.SKU.22\",\"title\":\"Soundbar Bluetooth A\",\"width\":140,\"height\":15,\"category\":\"Soundbar\",\"image\":\"https://lg.com/sku/\",\"country\":\"VN\"}]";


    public static final String DATA_SET = "[" +
            "{" +
                "\"id\":1," +
                "\"sku\":\"EXAM.SKU.01\"," +
                "\"title\":\"Smart Tivi 4K\"," +
                "\"width\":120," +
                "\"height\":40," +
                "\"category\":\"TIVI\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":2," +
                "\"sku\":\"EXAM.SKU.02\"," +
                "\"title\":\"Smart Tivi 4K Untra Width\"," +
                "\"width\":120," +
                "\"height\":40," +
                "\"category\":\"TIVI\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":3," +
                "\"sku\":\"EXAM.SKU.03\"," +
                "\"title\":\"Smart Tivi 2K\"," +
                "\"width\":120," +
                "\"height\":40," +
                "\"category\":\"TIVI\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":4," +
                "\"sku\":\"EXAM.SKU.04\"," +
                "\"title\":\"Smart Tivi 2K Untra Width\"," +
                "\"width\":120," +
                "\"height\":40," +
                "\"category\":\"TIVI\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":5," +
                "\"sku\":\"EXAM.SKU.05\"," +
                "\"title\":\"Smart Tivi 8K\"," +
                "\"width\":120," +
                "\"height\":40," +
                "\"category\":\"TIVI\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":6," +
                "\"sku\":\"EXAM.SKU.06\"," +
                "\"title\":\"Smart Tivi 8K Untra Width\"," +
                "\"width\":120," +
                "\"height\":40," +
                "\"category\":\"TIVI\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":7," +
                "\"sku\":\"EXAM.SKU.07\"," +
                "\"title\":\"Projector 4K\"," +
                "\"width\":20," +
                "\"height\":30," +
                "\"category\":\"Projector\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":8," +
                "\"sku\":\"EXAM.SKU.08\"," +
                "\"title\":\"Projector 4K Untra Width\"," +
                "\"width\":20," +
                "\"height\":30," +
                "\"category\":\"Projector\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":9," +
                "\"sku\":\"EXAM.SKU.09\"," +
                "\"title\":\"Projector 2K\"," +
                "\"width\":20," +
                "\"height\":30," +
                "\"category\":\"Projector\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":10," +
                "\"sku\":\"EXAM.SKU.10\"," +
                "\"title\":\"Projector 2K Untra Width\"," +
                "\"width\":20," +
                "\"height\":30," +
                "\"category\":\"Projector\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":11," +
                "\"sku\":\"EXAM.SKU.11\"," +
                "\"title\":\"Projector 8K\"," +
                "\"width\":20," +
                "\"height\":30," +
                "\"category\":\"Projector\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":12," +
                "\"sku\":\"EXAM.SKU.12\"," +
                "\"title\":\"Projector 8K Untra Width\"," +
                "\"width\":20," +
                "\"height\":30," +
                "\"category\":\"Projector\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":13," +
                "\"sku\":\"EXAM.SKU.13\"," +
                "\"title\":\"Soundbar Bluetooth A\"," +
                "\"width\":140," +
                "\"height\":15," +
                "\"category\":\"Soundbar\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"UK\"" +
            "},{" +
                "\"id\":14," +
                "\"sku\":\"EXAM.SKU.14\"," +
                "\"title\":\"Soundbar Bluetooth A\"," +
                "\"width\":140," +
                "\"height\":15," +
                "\"category\":\"Soundbar\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"UK\"" +
            "},{" +
                "\"id\":15," +
                "\"sku\":\"EXAM.SKU.15\"," +
                "\"title\":\"Soundbar Bluetooth A\"," +
                "\"width\":140," +
                "\"height\":15," +
                "\"category\":\"Soundbar\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"UK\"" +
            "},{" +
                "\"id\":16," +
                "\"sku\":\"EXAM.SKU.16\"," +
                "\"title\":\"Soundbar Bluetooth A\"," +
                "\"width\":140," +
                "\"height\":15," +
                "\"category\":\"Soundbar\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"UK\"" +
            "},{" +
                "\"id\":17," +
                "\"sku\":\"EXAM.SKU.17\"," +
                "\"title\":\"Soundbar Bluetooth A\"," +
                "\"width\":140," +
                "\"height\":15," +
                "\"category\":\"Soundbar\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":18," +
                "\"sku\":\"EXAM.SKU.18\"," +
                "\"title\":\"Soundbar Bluetooth A\"," +
                "\"width\":140," +
                "\"height\":15," +
                "\"category\":\"Soundbar\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":19," +
                "\"sku\":\"EXAM.SKU.19\"," +
                "\"title\":\"Soundbar Bluetooth A\"," +
                "\"width\":140," +
                "\"height\":15," +
                "\"category\":\"Soundbar\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":20," +
                "\"sku\":\"EXAM.SKU.20\"," +
                "\"title\":\"Soundbar Bluetooth A\"," +
                "\"width\":140," +
                "\"height\":15," +
                "\"category\":\"Soundbar\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":21," +
                "\"sku\":\"EXAM.SKU.21\"," +
                "\"title\":\"Soundbar Bluetooth A\"," +
                "\"width\":140," +
                "\"height\":15," +
                "\"category\":\"Soundbar\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "},{" +
                "\"id\":22," +
                "\"sku\":\"EXAM.SKU.22\"," +
                "\"title\":\"Soundbar Bluetooth A\"," +
                "\"width\":140," +
                "\"height\":15," +
                "\"category\":\"Soundbar\"," +
                "\"image\":\"https://lg.com/sku/\"," +
                "\"country\":\"VN\"" +
            "}]";
}