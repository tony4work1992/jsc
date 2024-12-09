package smartosc.jsc.applications.etl.mo_load_data.params;

import lombok.Data;

@Data
public class ColumnModel {
    private String id;
    private String sku;
    private String title;
    private String width;
    private String height;
    private String category;
    private String image;
    private String country;
}