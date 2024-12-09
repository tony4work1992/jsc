package smartosc.jsc.applications.etl.config;

import smartosc.jsc.applications.etl.constants.AppConstants;

public class AppConfig {
    public static final String CONFIG_NODES = "{\"0\":{\"id\":0,\"name\":\"LoadData\",\"config\":" + AppConstants.DATASET +",\"children\":[1],\"parent\":null},\"1\":{\"id\":1,\"name\":\"RenameColumns\",\"config\":[{\"column\":\"title\",\"newColumnName\":\"name\"}],\"children\":[2],\"parent\":[0]},\"2\":{\"id\":2,\"name\":\"Concat\",\"config\":[{\"newColumn\":\"sku_country\",\"concatColumn\":\"name,country\"}],\"children\":[3],\"parent\":[1]},\"3\":{\"id\":3,\"name\":\"Remove\",\"config\":[{\"column\":\"image\"}],\"children\":[4,5],\"parent\":[2]},\"4\":{\"id\":4,\"name\":\"Filter\",\"config\":[{\"column\":\"country\",\"comparisonOperator\":\"=\",\"comparisonValue\":\"VN\"}],\"children\":[],\"parent\":[3]},\"5\":{\"id\":5,\"name\":\"Filter\",\"config\":[{\"column\":\"category\",\"comparisonOperator\":\"=\",\"comparisonValue\":\"Soundbar\"}],\"children\":[],\"parent\":[3]},\"6\":{\"id\":6,\"name\":\"AddColumns\",\"config\":[{\"column\":\"salable\",\"value\":\"Y\"}],\"children\":[],\"parent\":[4]},\"7\":{\"id\":7,\"name\":\"AddColumns\",\"config\":[{\"column\":\"salable\",\"value\":\"N\"}],\"children\":[],\"parent\":[5]},\"8\":{\"id\":8,\"name\":\"Union\",\"config\":{},\"children\":[],\"parent\":[6,7]}}";
}
