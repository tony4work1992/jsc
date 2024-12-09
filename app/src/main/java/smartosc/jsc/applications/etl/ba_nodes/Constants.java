package smartosc.jsc.applications.etl.ba_nodes;

public final class Constants {

    private Constants() {
        throw new AssertionError("Cannot instantiate Constants class");
    }

    public static final String NODES = "{\n\"nodes\":{\n\"0\":{\n\"id\":0,\n\"name\":\"LOAD_COLUMNS\",\n\"config\":[\n{\n\"id\":1,\n\"sku\":\"EXAM.SKU.01\",\n\"title\":\"SmartTivi4K\",\n\"width\":120,\n\"height\":40,\n\"category\":\"TIVI\",\n\"image\":\"https://lg.com/sku/1\",\n\"country\":\"VN\"\n},\n{\n\"id\":2,\n\"sku\":\"EXAM.SKU.02\",\n\"title\":\"SmartTivi4KUntraWidth\",\n\"width\":120,\n\"height\":40,\n\"category\":\"TIVI\",\n\"image\":\"https://lg.com/sku/2\",\n\"country\":\"VN\"\n},\n{\n\"id\":3,\n\"sku\":\"EXAM.SKU.03\",\n\"title\":\"SmartTivi2K\",\n\"width\":120,\n\"height\":40,\n\"category\":\"TIVI\",\n\"image\":\"https://lg.com/sku/3\",\n\"country\":\"VN\"\n},\n{\n\"id\":4,\n\"sku\":\"EXAM.SKU.04\",\n\"title\":\"SmartTivi2KUntraWidth\",\n\"width\":120,\n\"height\":40,\n\"category\":\"TIVI\",\n\"image\":\"https://lg.com/sku/4\",\n\"country\":\"VN\"\n},\n{\n\"id\":5,\n\"sku\":\"EXAM.SKU.05\",\n\"title\":\"SmartTivi8K\",\n\"width\":120,\n\"height\":40,\n\"category\":\"TIVI\",\n\"image\":\"https://lg.com/sku/5\",\n\"country\":\"VN\"\n},\n{\n\"id\":6,\n\"sku\":\"EXAM.SKU.06\",\n\"title\":\"SmartTivi8KUntraWidth\",\n\"width\":120,\n\"height\":40,\n\"category\":\"TIVI\",\n\"image\":\"https://lg.com/sku/6\",\n\"country\":\"VN\"\n},\n{\n\"id\":7,\n\"sku\":\"EXAM.SKU.07\",\n\"title\":\"Projector4K\",\n\"width\":20,\n\"height\":30,\n\"category\":\"Projector\",\n\"image\":\"https://lg.com/sku/7\",\n\"country\":\"VN\"\n},\n{\n\"id\":8,\n\"sku\":\"EXAM.SKU.08\",\n\"title\":\"Projector4KUntraWidth\",\n\"width\":20,\n\"height\":30,\n\"category\":\"Projector\",\n\"image\":\"https://lg.com/sku/8\",\n\"country\":\"VN\"\n},\n{\n\"id\":9,\n\"sku\":\"EXAM.SKU.09\",\n\"title\":\"Projector2K\",\n\"width\":20,\n\"height\":30,\n\"category\":\"Projector\",\n\"image\":\"https://lg.com/sku/9\",\n\"country\":\"VN\"\n},\n{\n\"id\":10,\n\"sku\":\"EXAM.SKU.10\",\n\"title\":\"Projector2KUntraWidth\",\n\"width\":20,\n\"height\":30,\n\"category\":\"Projector\",\n\"image\":\"https://lg.com/sku/10\",\n\"country\":\"VN\"\n},\n{\n\"id\":11,\n\"sku\":\"EXAM.SKU.11\",\n\"title\":\"Projector8K\",\n\"width\":20,\n\"height\":30,\n\"category\":\"Projector\",\n\"image\":\"https://lg.com/sku/11\",\n\"country\":\"VN\"\n},\n{\n\"id\":12,\n\"sku\":\"EXAM.SKU.12\",\n\"title\":\"Projector8KUntraWidth\",\n\"width\":20,\n\"height\":30,\n\"category\":\"Projector\",\n\"image\":\"https://lg.com/sku/12\",\n\"country\":\"VN\"\n},\n{\n\"id\":13,\n\"sku\":\"EXAM.SKU.13\",\n\"title\":\"SoundbarBluetoothA\",\n\"width\":140,\n\"height\":15,\n\"category\":\"Soundbar\",\n\"image\":\"https://lg.com/sku/13\",\n\"country\":\"UK\"\n},\n{\n\"id\":14,\n\"sku\":\"EXAM.SKU.14\",\n\"title\":\"SoundbarBluetoothA\",\n\"width\":140,\n\"height\":15,\n\"category\":\"Soundbar\",\n\"image\":\"https://lg.com/sku/14\",\n\"country\":\"UK\"\n},\n{\n\"id\":15,\n\"sku\":\"EXAM.SKU.15\",\n\"title\":\"SoundbarBluetoothA\",\n\"width\":140,\n\"height\":15,\n\"category\":\"Soundbar\",\n\"image\":\"https://lg.com/sku/15\",\n\"country\":\"UK\"\n},\n{\n\"id\":16,\n\"sku\":\"EXAM.SKU.16\",\n\"title\":\"SoundbarBluetoothA\",\n\"width\":140,\n\"height\":15,\n\"category\":\"Soundbar\",\n\"image\":\"https://lg.com/sku/16\",\n\"country\":\"UK\"\n},\n{\n\"id\":17,\n\"sku\":\"EXAM.SKU.17\",\n\"title\":\"SoundbarBluetoothA\",\n\"width\":140,\n\"height\":15,\n\"category\":\"Soundbar\",\n\"image\":\"https://lg.com/sku/17\",\n\"country\":\"VN\"\n},\n{\n\"id\":18,\n\"sku\":\"EXAM.SKU.18\",\n\"title\":\"SoundbarBluetoothA\",\n\"width\":140,\n\"height\":15,\n\"category\":\"Soundbar\",\n\"image\":\"https://lg.com/sku/18\",\n\"country\":\"VN\"\n},\n{\n\"id\":19,\n\"sku\":\"EXAM.SKU.19\",\n\"title\":\"SoundbarBluetoothA\",\n\"width\":140,\n\"height\":15,\n\"category\":\"Soundbar\",\n\"image\":\"https://lg.com/sku/19\",\n\"country\":\"VN\"\n},\n{\n\"id\":20,\n\"sku\":\"EXAM.SKU.20\",\n\"title\":\"SoundbarBluetoothA\",\n\"width\":140,\n\"height\":15,\n\"category\":\"Soundbar\",\n\"image\":\"https://lg.com/sku/20\",\n\"country\":\"VN\"\n},\n{\n\"id\":21,\n\"sku\":\"EXAM.SKU.21\",\n\"title\":\"SoundbarBluetoothA\",\n\"width\":140,\n\"height\":15,\n\"category\":\"Soundbar\",\n\"image\":\"https://lg.com/sku/21\",\n\"country\":\"VN\"\n},\n{\n\"id\":22,\n\"sku\":\"EXAM.SKU.22\",\n\"title\":\"SoundbarBluetoothA\",\n\"width\":140,\n\"height\":15,\n\"category\":\"Soundbar\",\n\"image\":\"https://lg.com/sku/22\",\n\"country\":\"VN\"\n}\n],\n\"children\":[\n1\n],\n\"parent\":[]\n},\n\"1\":{\n\"id\":1,\n\"name\":\"RENAME_COLUMNS\",\n\"config\":[\n{\n\"column\":\"title\",\n\"newColumn\":\"name\"\n}\n],\n\"children\":[\n2\n],\n\"parent\":[\n0\n]\n},\n\"2\":{\n\"id\":2,\n\"name\":\"CONCAT_COLUMNS\",\n\"config\":[\n{\n\"column\":\"name_country\",\n\"listColumn\":\"name,country\"\n}\n],\n\"children\":[\n3\n],\n\"parent\":[\n1\n]\n},\n\"3\":{\n\"id\":3,\n\"name\":\"REMOVE_COLUMNS\",\n\"config\":[\n{\n\"column\":\"image\"\n}\n],\n\"children\":[\n4,\n5\n],\n\"parent\":[\n2\n]\n},\n\"4\":{\n\"id\":4,\n\"name\":\"FILTER_COLUMNS\",\n\"config\":[\n{\n\"column\":\"country\",\n\"operator\":\"=\",\n\"value\":\"VN\"\n}\n],\n\"children\":[\n6\n],\n\"parent\":[\n3\n]\n},\n\"5\":{\n\"id\":5,\n\"name\":\"FILTER_COLUMNS\",\n\"config\":[\n{\n\"column\":\"category\",\n\"operator\":\"=\",\n\"value\":\"Soundbar\"\n}\n],\n\"children\":[\n7\n],\n\"parent\":[\n3\n]\n},\n\"6\":{\n\"id\":6,\n\"name\":\"ADD_COLUMNS\",\n\"config\":[\n{\n\"column\":\"salable\",\n\"value\":\"Y\"\n}\n],\n\"children\":[\n8\n],\n\"parent\":[\n4\n]\n},\n\"7\":{\n\"id\":7,\n\"name\":\"ADD_COLUMNS\",\n\"config\":[\n{\n\"column\":\"salable\",\n\"value\":\"N\"\n}\n],\n\"children\":[\n8\n],\n\"parent\":[\n5\n]\n},\n\"8\":{\n\"id\":8,\n\"name\":\"UNION_COLUMNS\",\n\"config\":[\n{\n\"nodeIds\":\"6,7\"\n}\n],\n\"children\":[],\n\"parent\":[\n6,\n7\n]\n}\n}\n}";
    public static final String OPERATOR_EQUALS = "=";
    public static final String OPERATOR_GREATER_THAN = ">";
    public static final String OPERATOR_LESS_THAN = "<";
    public static final String OPERATOR_LIKE = "like";
}